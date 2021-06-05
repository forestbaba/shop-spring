package com.forestsoftware.usershop.model;

public class MessageResponse {
    public String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public void setMessage(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
