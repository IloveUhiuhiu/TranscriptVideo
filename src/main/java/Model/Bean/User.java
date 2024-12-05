package Model.Bean;

import java.time.LocalDateTime;

public class User {
	private String user_id;
	private String email;
	private String password;
	private LocalDateTime create_at;
	public User() {
		
	}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreate_at() {
		return create_at;
	}
	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}
	
}
