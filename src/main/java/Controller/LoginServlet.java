package Controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.BO.GetCookie;
import Model.BO.UserBO;
import Model.Bean.User;



@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserBO userBO = new UserBO();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("User") == null) {
            String errorString = (String) request.getAttribute("errorString");
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorString", "Tên tài khoản và mật khẩu không được để trống.");
            doGet(request, response);
            return;
        }

       User user = userBO.isValidUser(username, password);   
        if (user != null) {
            request.getSession().setAttribute("User", user);
            if (remember){    
               GetCookie.storeUserCookie(response, user);
            } else {
                GetCookie.deleteUserCookie(response);
            }
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            request.setAttribute("errorString", "Sai tên tài khoản hoặc mật khẩu.");
            doGet(request, response);
        }
    }
}

