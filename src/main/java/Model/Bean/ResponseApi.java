package Model.Bean;

public class ResponseApi {
	public ResponseApi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseApi(String srtUrl, String txtUrl) {
		super();
		this.srtUrl = srtUrl;
		this.txtUrl = txtUrl;
	}
	private String srtUrl;
	private String txtUrl;
	public String getSrtUrl() {
		return srtUrl;
	}
	public void setSrtUrl(String srtUrl) {
		this.srtUrl = srtUrl;
	}
	public String getTxtUrl() {
		return txtUrl;
	}
	public void setTxtUrl(String txtUrl) {
		this.txtUrl = txtUrl;
	}
	
	
	

}
