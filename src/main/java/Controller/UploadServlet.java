package Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.*;

import Model.Bean.*;
import Model.BO.*;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String UPLOAD_DIRECTORY;
	private VideoBo videoBo = new VideoBo();
	private LogBo logBo = new LogBo();
	private SubtitleBo subBo = new SubtitleBo();
	private LanguageBo langBo = new LanguageBo();
	private ThreadPoolExecutor executorService; 
	private BlockingQueue<RequestUpload> videoQueue = new LinkedBlockingQueue<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
        super.init();
        
        UPLOAD_DIRECTORY = "D:/LT_Mang/transcriptvideo";
        executorService = new ThreadPoolExecutor(
        	    5, // Số lượng luồng tối thiểu
        	    10, // Số lượng luồng tối đa
        	    60L, // Thời gian tồn tại nếu một luồng rảnh
        	    TimeUnit.SECONDS, // Đơn vị là s
        	    new LinkedBlockingQueue<Runnable>() // Hàng đợi nếu 10 luồng đều bận
        	);
        new Thread(new VideoQueueProcessor()).start();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent( request)) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest((javax.servlet.http.HttpServletRequest) request); 

                int languageId = 0;
                File videoFile = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("language")) {
                            languageId = Integer.parseInt(item.getString());
                        }
                    } else {
                        if (item.getFieldName().equals("video")) {
                            String fileName = new File(item.getName()).getName();
                            videoFile = new File(UPLOAD_DIRECTORY, fileName);
                            item.write(videoFile);
                        }
                    }
                }
                
                Video videoUpload = videoBo.addVideo(videoFile.getAbsolutePath());
//                response.getWriter().println("VideoId: " + videoUpload.getVideoId());
//                response.getWriter().println("OriginalUrl: " + videoUpload.getOriginalUrl());
//                response.getWriter().println("ResultUrl: " + videoUpload.getResultUrl());
//                response.getWriter().println("Status: " + videoUpload.getStatus());
//                response.getWriter().println("TimeUpload: " + videoUpload.getTimeUpload().toString());
//                response.getWriter().println("UserId: " + videoUpload.getUserId());
                //Language lang = langBo.getById(languageId);
                videoQueue.add(new RequestUpload(videoUpload.getVideoId(),videoUpload.getOriginalUrl(),languageId));
                
                
                request.setAttribute("videoId", videoUpload.getVideoId());
                
                request.getRequestDispatcher("Process.jsp").forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("File upload failed: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Request content type is not multipart/form-data");
        }
    }
    private class VideoQueueProcessor implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                	if (!videoQueue.isEmpty()) {
                		RequestUpload request = videoQueue.take();
                        executorService.submit(() -> processVideo(request));
                	}
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; 
                }
            }
        }

        private void processVideo(RequestUpload request) {
            try {
            	
            	String audioUrl = UPLOAD_DIRECTORY +  "/audio" + request.getVideoId() + ".mp3";
            	try {
            		videoBo.updateStatus(request.getVideoId(), "PROCESSING");
            		logBo.createLog(request.getVideoId(), "Đang tạo audio... ");
            		videoBo.createAudio(request.getOriginalUrl(),audioUrl);
            		
            	} catch (Exception e) {
            		System.out.println("Lỗi trong khi tạo Audio");
            		// Log ghi lỗi ở đây
            		videoBo.updateStatus(request.getVideoId(), "FAILED");
            		logBo.createLog(request.getVideoId(), "Lỗi trong quá trình tạo audio...");
            		return;
            	}
            	
            	ResponseApi response;
                try {
                	logBo.createLog(request.getVideoId(), "Đang tạo Subtitle...");
                	Language lang = langBo.getById(request.getLanguageId());
                	response = videoBo.createSubtitle(audioUrl, lang.getKey());
                	subBo.addSubtitle(response.getSrtUrl(),response.getTxtUrl(),request.getVideoId(),request.getLanguageId());
                } catch (Exception e) {
                	System.out.println("Lỗi trong khi tạo Subtitle");
                	// Log ghi lỗi ở đây
                	videoBo.updateStatus(request.getVideoId(), "FAILED");
                	logBo.createLog(request.getVideoId(), "Lỗi trong quá trình tạo Subtile...");
                	return;
                }
                String resultUrl = null;
                try {
                	logBo.createLog(request.getVideoId(), "Đang gắn Subtitle lên Video gốc...");
                	resultUrl = videoBo.setSubclip(response.getSrtUrl(), request.getOriginalUrl());
                } catch (Exception e) {
                	System.out.println("Lỗi trong khi tạo Result");
                	// Log ghi lỗi ở đây
                	videoBo.updateStatus(request.getVideoId(), "FAILED");
                	logBo.createLog(request.getVideoId(), "Lỗi trong quá trình tạo kết quả...");
                	return;
                }
                System.out.println("Thành công");
                videoBo.updateStatus(request.getVideoId(), "COMPLETED");
                videoBo.updateResult(request.getVideoId(), resultUrl);
                logBo.createLog(request.getVideoId(), "Quá trình kết thúc thành công...");

            } catch (Exception e) {
                e.printStackTrace();
                
            }
        }
    }

    @Override
    public void destroy() {
        executorService.shutdown(); 
        super.destroy();
    }


}

