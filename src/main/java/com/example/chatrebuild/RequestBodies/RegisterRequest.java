package com.example.chatrebuild.RequestBodies;

public class RegisterRequest {
    String username;
    String password;
    String email;
    String activation;

    public RegisterRequest(String username, String password, String email, String activation) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activation = activation;
    }

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }
}
