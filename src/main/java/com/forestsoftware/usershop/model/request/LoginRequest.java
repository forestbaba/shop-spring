package com.forestsoftware.usershop.model.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty(message = "email is required")
    private String email;

    @NotEmpty(message = "password is required")
    private String password;

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
}
