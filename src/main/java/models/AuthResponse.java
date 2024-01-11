package models;

public class AuthResponse {
    private String message;
    private User user;

    // Constructor, getters, and setters

    public AuthResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
