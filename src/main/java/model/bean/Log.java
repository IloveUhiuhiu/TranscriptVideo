package model.bean;

import java.time.LocalDateTime;

public class Log {
	public Log(String logId, String videoId, String message ,LocalDateTime createAt) {
		super();
		this.logId = logId;
		this.videoId = videoId;
		this.message = message;
		this.createAt = createAt;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String logId;
	private String videoId;
	private String message;
	private LocalDateTime createAt;
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	
	
}
