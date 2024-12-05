package model.dao;

import java.time.LocalDateTime;

import model.bean.Video;

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
	
	

}
