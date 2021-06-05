package com.forestsoftware.usershop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhoneAlreadyExistException extends RuntimeException {
    public PhoneAlreadyExistException(String message) {
        super(message);
    }
}
