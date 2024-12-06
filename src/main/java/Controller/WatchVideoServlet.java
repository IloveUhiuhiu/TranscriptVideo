package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/watch")
public class WatchVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public WatchVideoServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // Lấy tham số fileName từ query string
        String videoPath = request.getParameter("fileName");

        if (videoPath == null || videoPath.isEmpty()) {
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"videoPath is required\"}");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File videoFile = new File(videoPath);
        if (!videoFile.exists() || videoFile.isDirectory()) {
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"File not found: " + videoPath + "\"}");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Stream video trực tiếp tới trình duyệt
        response.setContentType("video/mp4");
        response.setContentLengthLong(videoFile.length());
        response.setHeader("Content-Disposition", "inline; filename=\"" + videoFile.getName() + "\"");

        try (FileInputStream fileInputStream = new FileInputStream(videoFile)) {
            byte[] buffer = new byte[1024 * 10]; // 10 KB buffer
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
