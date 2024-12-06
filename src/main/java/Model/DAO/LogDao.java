package Model.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Bean.*;

public class LogDao {
	Connection conn = null;
	
	public void createLog(String logId, String videoId, String message, LocalDateTime createAt) {
		if (conn == null) {
            try {
                conn = ConnectDatabase.getMySQLConnection();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        String insert = "INSERT INTO log (log_id, video_id, message, create_at) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement preSt = conn.prepareStatement(insert);
            preSt.setString(1, logId);
            preSt.setString(2, videoId);
            preSt.setString(3, message);
            preSt.setTimestamp(4, Timestamp.valueOf(createAt));
            preSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           //cleanResources();
            
        }
	}
	
	public List<Log> getLogs(String videoId) {
			if (conn == null)
				try {
					conn = ConnectDatabase.getMySQLConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			String sql = "SELECT * FROM log WHERE video_id=?";
			List<Log> logs = new ArrayList<>();
			
			try {
				PreparedStatement preSt = conn.prepareStatement(sql);
				preSt.setString(1, videoId);
				
				ResultSet rs = preSt.executeQuery();
				while (rs.next()) {
					String logId = rs.getString("log_id");
					String message = rs.getString("message");
					LocalDateTime createAt =rs.getTimestamp("create_at").toLocalDateTime();
					logs.add(new Log(logId,videoId,message,createAt));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//cleanResources();
			}
			
			return logs;
	}
	public Log getNewLog(String videoId) {
		List<Log> logs = getLogs(videoId);
		Collections.sort(logs, (log1, log2) -> log1.getCreateAt().compareTo(log2.getCreateAt()));
		int n = logs.size();
		if (n > 0) {
			return logs.get(n - 1);
		}
		return null;
		
	}
	private void cleanResources() {
		try {
            if (conn != null) {
                conn.close(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
