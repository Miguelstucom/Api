package models;

public class changePasswordRequest {
	private String lastpassword;
	private String newpassword;
	private String token;
	
	public String getLastpassword() {
		return lastpassword;
	}
	public void setLastpassword(String lastpassword) {
		this.lastpassword = lastpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
