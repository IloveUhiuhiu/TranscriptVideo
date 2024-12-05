package Model.BO;

import java.sql.SQLException;

import Model.Bean.User;
import Model.DAO.UserDAO;


public class UserBO {
	private UserDAO userDAO = new UserDAO();

	public User isValidUser(String username, String password) {
		User user = new User();
		user.setEmail(username);
		user.setPassword(password);
		return userDAO.isValidUser(username, password);
	}
	
	public boolean registerUser(User user) throws SQLException {
		 if (userDAO.isEmailExists(user.getEmail())) {
	            return false;
	        }
	     return userDAO.registerUser(user);
	}
}
