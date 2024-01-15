package models;

public class AuthResponse {
    private String message;
    private User user;


    public AuthResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
