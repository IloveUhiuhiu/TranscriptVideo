package model.dao;

import java.time.LocalDateTime;
import java.sql.*;

public class SubtitleDao {
	Connection conn = null;


	public void addSubtitle(String subtitleId, String srtUrl, String txtUrl, LocalDateTime createAt, String videoId,
			int languageId) {
		
		if (conn == null) {
            try {
                conn = ConnectDatabase.getMySQLConnection();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
		
		String insert = "INSERT INTO subtitle (subtitle_id, srt_url, txt_url, create_at, video_id, language_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preSt = conn.prepareStatement(insert);
            preSt.setString(1, subtitleId);
            preSt.setString(2, srtUrl);
            preSt.setString(3, txtUrl);
            preSt.setTimestamp(4, Timestamp.valueOf(createAt));
            preSt.setString(5,videoId);
            preSt.setInt(6,languageId);
            preSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           //cleanResources();
            
        }
	}

}
