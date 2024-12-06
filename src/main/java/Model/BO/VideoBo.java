package Model.BO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Controller.UploadServlet;
import Model.Bean.ResponseApi;
import Model.Bean.Video;
import Model.DAO.*;
public class VideoBo {
	private VideoDao videoDao = new VideoDao();
	private String serverUrl = "https://be56-34-118-240-13.ngrok-free.app/transcribe";
	public Video addVideo(String originalUrl) {
		
		String videoId = getUUID();
		String status = "PENDING";
		LocalDateTime timeUpload = LocalDateTime.now();
		String userId = "b731100d";
		videoDao.addVideo(videoId,originalUrl,status,timeUpload,userId);
		
		return new Video(videoId,originalUrl, null, status, timeUpload, userId);
	}
	
	public void createAudio(String videoInput, String audioOutput) {
		try {
			createEmptyMp3(audioOutput);
		} catch (Exception e) {
			System.out.println("Error create empty Mp3: " + e.getMessage());
			return;
		}
		String ffmpegCommand = "ffmpeg -i " + videoInput + " -f mp3 -ab 192000 -vn " + audioOutput;
		System.out.println(ffmpegCommand);
     
        ProcessBuilder ffmpegProcess = new ProcessBuilder("cmd.exe", "/c", ffmpegCommand);
        
        try {
            Process process1 = ffmpegProcess.start();
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
            String line1;
            while ((line1 = reader1.readLine()) != null) {
                System.out.println(line1);
            }


            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process1.getErrorStream()));
            String lineError;
            while ((lineError = errorReader.readLine()) != null) {
                System.err.println(lineError);
            }

            int exitCode = process1.waitFor();
            System.out.println("FFmpeg exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}
	public void createEmptyMp3(String audioOutput) {
        File outputFile = new File(audioOutput);
        if (outputFile.exists()) {
        	System.out.println("Tạo thành công");
        } else {
        	System.out.println("Không thể tạo thành công");
        }
    }
	public void updateStatus(String videoId, String status) {
		videoDao.updateStatus(videoId, status);
	}
	public static void renameFile(String oldPath, String newPath) {
		File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        boolean success = oldFile.renameTo(newFile);
        if (success) {
            System.out.println("Tệp đã được đổi tên thành công.");
        } else {
            System.out.println("Không thể đổi tên tệp.");
        }
	}
	public static void deleteFile(String path) {
		File fileToDelete = new File(path);
        boolean success = fileToDelete.delete();
        if (success) {
            System.out.println("Tệp đã được xóa thành công.");
        } else {
            System.out.println("Không thể xóa tệp. Có thể tệp không tồn tại hoặc đang được sử dụng.");
        }
	}
	public ResponseApi createSubtitle(String audioUrl, String language) {
		//System.out.println(audioUrl);
		File audioFile = new File(audioUrl);
		String boundary = Long.toHexString(System.currentTimeMillis());
		String CRLF = "\r\n";
		 HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(serverUrl).openConnection();
			
			
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			
			
			try (OutputStream output = connection.getOutputStream();
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(output,"UTF-8"))) {
					writer.append("--" + boundary).append(CRLF);
					writer.append("Content-Disposition: form-data; name=\"audio\"; filename=\"" + audioFile.getName() + "\"").append(CRLF);
					writer.append("Content-Type: audio/mpeg").append(CRLF);
					writer.append(CRLF).flush();
					
					try (FileInputStream input = new FileInputStream(audioFile)) {
						byte[] buffer = new byte[4096];
						int bytesRead;
						while((bytesRead = input.read(buffer)) != -1) {
							output.write(buffer,0,bytesRead);
						}
					}
					
					output.flush();
					
					writer.append(CRLF).append("--" + boundary).append(CRLF);
					writer.append("Content-Disposition: form-data; name=\"language\"").append(CRLF);
					writer.append(CRLF).append(language).append(CRLF).flush();
					
					writer.append(CRLF).append("--" + boundary + "--").append(CRLF).flush();
			} 
			
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream input = connection.getInputStream();FileOutputStream output = new FileOutputStream("D:/LT_Mang/transcriptvideo/output.zip")) {
					byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    
                    
                    String[] extractedFiles = unzip("D:/LT_Mang/transcriptvideo/output.zip","D:/LT_Mang/transcriptvideo");
                    System.out.println("Tệp đã tải xuống thành công.");
                    System.out.println("Tệp SRT: " + extractedFiles[0]);
                    System.out.println("Tệp TXT: " + extractedFiles[1]);
					
                    return new ResponseApi(extractedFiles[0],extractedFiles[1]);
				}
			} else {
				System.out.println("Lỗi: " + responseCode);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
                connection.disconnect();
            }
		}
		
		return null;
	}
	private static String convertPath(String path) {
		String convertedPath = path.replace("\\", "/");
		return convertedPath;
	}
	private static String fixSubtitlesPath(String path) {
        
       
        int colonIndex = path.indexOf(":");
        if (colonIndex > 0 && path.charAt(colonIndex - 1) != '\\') {
            path = path.substring(0, colonIndex) + "\\" + path.substring(colonIndex);
        }

        return path;
    }
	public String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 8);
	}
	public String setSubclip(String srtUrl, String originalUrl) {
		
        String inputVideoPath = convertPath(originalUrl);
        String subtitlesPath = fixSubtitlesPath(convertPath(srtUrl));
        
        String outputVideoPath = convertPath("D:/LT_Mang/transcriptvideo/" + "result" + getUUID() + ".mp4");
        System.out.println(inputVideoPath);
        System.out.println(subtitlesPath);
        System.out.println(outputVideoPath);
        

        try {
            // Lệnh FFmpeg
            String[] command = {
                "ffmpeg","-y",
                "-i", inputVideoPath,
                "-vf", "subtitles=\'" + subtitlesPath + "\'",
                "-c:a", "copy",
                outputVideoPath
            };

            // Khởi tạo ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Gộp cả stdout và stderr để xem toàn bộ đầu ra
            processBuilder.redirectErrorStream(true);

            // Chạy lệnh
            Process process = processBuilder.start();

            // Đọc đầu ra của lệnh
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // In từng dòng output ra console
            }

            // Chờ tiến trình kết thúc
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

            // Kiểm tra exit code để xác định thành công hay thất bại
            if (exitCode == 0) {
                System.out.println("FFmpeg process completed successfully.");
            } else {
                System.out.println("FFmpeg process failed. Please check the logs.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputVideoPath;
	}
	
	
	public static String[] unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs(); // Tạo thư mục nếu không tồn tại

        String srtFilePath = null;
        String txtFilePath = null;

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                File newFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    // Đảm bảo thư mục cha tồn tại
                    new File(newFile.getParent()).mkdirs();
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            bos.write(buffer, 0, len);
                        }
                    }

                    // Lưu đường dẫn của tệp .srt và .txt
                    if (entry.getName().endsWith(".srt")) {
                        srtFilePath = newFile.getAbsolutePath();
                    } else if (entry.getName().endsWith(".txt")) {
                        txtFilePath = newFile.getAbsolutePath();
                    }
                }
                zipInputStream.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(zipFilePath);
        return new String[] {srtFilePath, txtFilePath}; // Trả về đường dẫn tệp
    }
	
	public void updateResult(String videoId, String resultUrl) {
		videoDao.updateResult(videoId,resultUrl);
	}
	 public List<Video> getVideoHistoryByEmail(String email) throws ClassNotFoundException {
	    return videoDao.getVideoHistoryByEmail(email);
	}
}
