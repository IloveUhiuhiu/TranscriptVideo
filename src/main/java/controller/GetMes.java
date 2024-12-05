package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Log;
import model.bo.*;
/**
 * Servlet implementation class GetMes
 */
@WebServlet("/GetMes")
public class GetMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LogBo logBo = new LogBo();
    
    public GetMes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String videoId = request.getParameter("videoId");
        System.out.println(videoId);
        Log log = logBo.getNewLog(videoId);
        
        // Đặt loại nội dung là JSON
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Tạo một đối tượng JSON
        String jsonResponse = String.format("{\"message\": \"%s\"}", log.getMessage());
        
        out.print(jsonResponse);
        out.close();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
