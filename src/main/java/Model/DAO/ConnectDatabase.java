package Model.DAO;

import java.sql.*;
public class ConnectDatabase {
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException
	{	
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transcriptvideo","root","21012004");
			if (conn != null) {
				System.out.println("Kết nối thành công");
				return conn;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
