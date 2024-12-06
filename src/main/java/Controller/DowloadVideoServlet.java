package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dowload")
public class DowloadVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DowloadVideoServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	
			String fileName = request.getParameter("fileName");
	        
	        
	        File file = new File(fileName);
	        if (file.exists()) {
	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	            
	            try (FileInputStream fileInputStream = new FileInputStream(file);
	                 ServletOutputStream outputStream = response.getOutputStream()) {
	                byte[] buffer = new byte[4096];
	                int bytesRead;
	                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	            }
	        } else {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
	        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
