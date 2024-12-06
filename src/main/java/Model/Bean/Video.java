package Model.Bean;

import java.time.LocalDateTime;

public class Video {
	public Video(String videoId, String originalUrl, String resultUrl, String status, LocalDateTime timeUpload,
			String userId) {
		super();
		this.videoId = videoId;
		this.originalUrl = originalUrl;
		this.resultUrl = resultUrl;
		this.status = status;
		this.timeUpload = timeUpload;
		this.userId = userId;
	}
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String videoId;
	private String originalUrl;
	private String resultUrl;
	private String status;
	private LocalDateTime timeUpload;
	private String userId;
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getTimeUpload() {
		return timeUpload;
	}
	public void setTimeUpload(LocalDateTime timeUpload) {
		this.timeUpload = timeUpload;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
