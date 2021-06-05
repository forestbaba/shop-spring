package com.forestsoftware.usershop.exceptions;

public class EmailAlreadyExistResponse {
    private String username;

    public EmailAlreadyExistResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
