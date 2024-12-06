package Model.BO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import Model.Bean.*;
import Model.DAO.*;

public class LogBo {
	private LogDao logDao = new LogDao();
	
	public void createLog(String videoId, String message) {
		UUID uuid = UUID.randomUUID();
		String logId = uuid.toString().substring(0, 8);
		LocalDateTime createAt = LocalDateTime.now();
		logDao.createLog(logId,videoId,message,createAt);
		
	}
	public List<Log> getLogs(String videoId) {
		return logDao.getLogs(videoId);
	}
	
	
	public Log getNewLog(String videoId) {
		return logDao.getNewLog(videoId);
	}

}
