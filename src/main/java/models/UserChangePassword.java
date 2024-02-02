package models;

public class UserChangePassword {
    private String email;
    private String password;
    private String newPassword;
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    // getters y setters
}
