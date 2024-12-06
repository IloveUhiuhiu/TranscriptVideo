package Model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.*;

public class LanguageDao {
	
	Connection conn = null;
//	Statement st = null;
//	PreparedStatement preSt = null;
	public Language getById(int id) {
		if (conn == null)
			try {
				conn = ConnectDatabase.getMySQLConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		String sql = "SELECT * FROM language WHERE language_id=?";
		List<Language> langs = new ArrayList<>();
		
		try {
			PreparedStatement preSt = conn.prepareStatement(sql);
			preSt.setInt(1, id);
			
			ResultSet rs = preSt.executeQuery();
			while (rs.next()) {
				int languageId = rs.getInt("language_id");
				String key = rs.getString("key");
				String name = rs.getString("name");
				langs.add(new Language(languageId,key,name));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//cleanResources();
		}
		
		if (langs.size() == 0) return null;
		return langs.get(0);
	}
	
	public List<Language> getAll() {
		if (conn == null)
			try {
				conn = ConnectDatabase.getMySQLConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		String sql = "SELECT * FROM language";
		List<Language> langs = new ArrayList<>();
		
		try {
			PreparedStatement preSt = conn.prepareStatement(sql);
			ResultSet rs = preSt.executeQuery();
			while (rs.next()) {
				int languageId = rs.getInt("language_id");
				String key = rs.getString("key");
				String name = rs.getString("name");
				langs.add(new Language(languageId,key,name));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//cleanResources();
		}
		
		return langs;
	}

}
