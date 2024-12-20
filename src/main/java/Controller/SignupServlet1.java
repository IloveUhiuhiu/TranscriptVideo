package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Bean.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.BO.UserBO;
import javax.servlet.RequestDispatcher;


@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBO userBO = new UserBO();
       
    
    public SignupServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("errorString", "All fields are required.");
            forwardToSignupPage(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorString", "Passwords do not match.");
            forwardToSignupPage(request, response);
            return;
        }

        User user = new User(email, password);

        try {
            boolean isRegistered = userBO.registerUser(user);
            if (isRegistered) {
            	System.out.print("thêm thành công");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                request.setAttribute("errorString", "Email already exists.");
                forwardToSignupPage(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorString", "An error occurred while processing your request.");
            forwardToSignupPage(request, response);
        }
	}
	 private void forwardToSignupPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
	        dispatcher.forward(request, response);
	    }

}
