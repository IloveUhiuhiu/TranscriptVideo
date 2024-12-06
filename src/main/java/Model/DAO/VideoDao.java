package Model.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Video;

import java.sql.*;
public class VideoDao {
	Connection conn = null;
	
	
	public void addVideo(String videoId, String originalUrl, String status, LocalDateTime timeUpload, String userId) {
		
		if (conn == null) {
            try {
                conn = ConnectDatabase.getMySQLConnection();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        String insert = "INSERT INTO video (video_id, original_url, status, time_upload, user_id) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preSt = conn.prepareStatement(insert);
            preSt.setString(1, videoId);
            preSt.setString(2, originalUrl);
            preSt.setString(3, status);
            preSt.setTimestamp(4, Timestamp.valueOf(timeUpload));
            preSt.setString(5, userId);
            preSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           //cleanResources();
            
        }
		
		
		
	}
	public void updateStatus(String videoId, String status) {
		if (conn == null) {
            try {
                conn = ConnectDatabase.getMySQLConnection();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
		String sql = "UPDATE video SET status =? where video_id=? ";
		try {
			PreparedStatement preSt = conn.prepareStatement(sql);
			preSt.setString(1,status);
			preSt.setString(2, videoId);
			preSt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//cleanResources();
		}
		
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
	public void updateResult(String videoId, String resultUrl) {
		if (conn == null) {
			try {
				conn = ConnectDatabase.getMySQLConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String sql = "UPDATE video SET result_url =? where video_id=? ";
		try {
			PreparedStatement preSt = conn.prepareStatement(sql);
			preSt.setString(1,resultUrl);
			preSt.setString(2, videoId);
			preSt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//cleanResources();
		}
			
	}
	
	public List<Video> getVideoHistoryByEmail(String email) throws ClassNotFoundException {
	    List<Video> videoHistory = new ArrayList<>();
	    String getUserQuery = "SELECT user_id FROM user WHERE email = ?";
	    String getVideoQuery = "SELECT * FROM video WHERE user_id = ? ORDER BY time_upload DESC";

	    try (Connection conn = ConnectDatabase.getMySQLConnection()) {
	        // Lấy user_id từ email
	        String userId = null;
	        try (PreparedStatement userStmt = conn.prepareStatement(getUserQuery)) {
	            userStmt.setString(1, email);
	            ResultSet userRs = userStmt.executeQuery();
	            if (userRs.next()) {
	                userId = userRs.getString("user_id");
	            }
	        }

	        // Nếu userId không tồn tại, trả về danh sách video rỗng
	        if (userId == null) {
	            System.out.println("Không tìm thấy user với email: " + email);
	            return videoHistory;
	        }

	        // Lấy danh sách video từ user_id
	        try (PreparedStatement videoStmt = conn.prepareStatement(getVideoQuery)) {
	            videoStmt.setString(1, userId);
	            ResultSet videoRs = videoStmt.executeQuery();
	            while (videoRs.next()) {
	                Video video = new Video(
	                    videoRs.getString("video_id"),
	                    videoRs.getString("original_url"),
	                    videoRs.getString("result_url"),
	                    videoRs.getString("status"),
	                    videoRs.getObject("time_upload", LocalDateTime.class),
	                    videoRs.getString("user_id")
	                );
	                videoHistory.add(video);
	            }
	        }

	        if (videoHistory.isEmpty()) {
	            System.out.println("Không có video nào được tìm thấy cho user_id: " + userId);
	        } else {
	            System.out.println("Đã tìm thấy video cho user_id: " + userId);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return videoHistory;
	}


}
