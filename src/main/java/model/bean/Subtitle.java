package model.bean;

import java.time.LocalDateTime;

public class Subtitle {
	public Subtitle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subtitle(String subtitleId, String srtId, String txtId, LocalDateTime createAt, String videoId,
			String languageId) {
		super();
		this.subtitleId = subtitleId;
		this.srtId = srtId;
		this.txtId = txtId;
		this.createAt = createAt;
		this.videoId = videoId;
		this.languageId = languageId;
	}
	private String subtitleId;
	private String srtId;
	private String txtId;
	private LocalDateTime createAt;
	private String videoId;
	private String languageId;
	public String getSubtitleId() {
		return subtitleId;
	}
	public void setSubtitleId(String subtitleId) {
		this.subtitleId = subtitleId;
	}
	public String getSrtId() {
		return srtId;
	}
	public void setSrtId(String srtId) {
		this.srtId = srtId;
	}
	public String getTxtId() {
		return txtId;
	}
	public void setTxtId(String txtId) {
		this.txtId = txtId;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	

}
