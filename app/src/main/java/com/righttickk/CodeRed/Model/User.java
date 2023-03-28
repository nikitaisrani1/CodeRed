package com.righttickk.CodeRed.Model;

public class User {
    private String email;
    private String id;
    private String username;

    public User() {
    }

    public User(String email, String id, String username) {
        this.email = email;
        this.id = id;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
