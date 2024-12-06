package Model.Bean;

public class Language {
	public Language(int languageId, String key, String name) {
		super();
		this.languageId = languageId;
		this.key = key;
		this.name = name;
	}
	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private int languageId;
	private String key;
	private String name;
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
