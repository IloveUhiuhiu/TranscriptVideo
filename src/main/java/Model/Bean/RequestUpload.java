package Model.Bean;

public class RequestUpload {
	public RequestUpload(String videoId, String originalUrl, int languageId) {
		super();
		this.videoId = videoId;
		this.originalUrl = originalUrl;
		this.languageId = languageId;
	}
	public RequestUpload() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String videoId;
	private String originalUrl;
	private int languageId;
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
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

}
