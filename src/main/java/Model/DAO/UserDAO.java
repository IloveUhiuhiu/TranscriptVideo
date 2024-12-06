package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
        String query = "INSERT INTO user (user_id, email, password, create_at) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
        	String user_id = UUID.randomUUID().toString().substring(0, 8);
        	String create_at = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        	statement.setString(1, user_id); 
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword()); 
            statement.setString(4, create_at);
            return statement.executeUpdate() > 0;
        }
    }
}
