package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Bean.User;



public class UserDAO {
	public User isValidUser(String email, String password) {
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";
		try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setString(1, email);
	            ps.setString(2, password);

	            ResultSet rs = ps.executeQuery();
	           while(rs.next()) {
	                User user = new User();
	                user.setEmail(rs.getString("email"));
	                user.setPassword(rs.getString("password"));
	                return user;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	public boolean isEmailExists(String email) throws SQLException {
        String query = "SELECT email FROM user WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        }
    }
	public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (email, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            return statement.executeUpdate() > 0;
        }
    }
}
