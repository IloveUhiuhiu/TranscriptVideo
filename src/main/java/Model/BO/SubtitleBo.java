package Model.BO;
import java.time.LocalDateTime;
import java.util.UUID;

import Model.DAO.*;

public class SubtitleBo {
	private SubtitleDao subtitleDao = new SubtitleDao();
	
	public void addSubtitle(String srtUrl, String txtUrl, String videoId, int languageId) {
		UUID uuid = UUID.randomUUID();
		String subtitleId = uuid.toString().substring(0, 8);
		LocalDateTime createAt = LocalDateTime.now();
		
		subtitleDao.addSubtitle(subtitleId, srtUrl, txtUrl, createAt, videoId, languageId);
	}

}
