package com.forestsoftware.usershop.exceptions;

public class PhoneAlreadyExistResponse {
    private String username;

    public PhoneAlreadyExistResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
