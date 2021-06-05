package com.forestsoftware.usershop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


 public final ResponseEntity<Object> handleUsernameAlreadyExist(EmailAlreadyExistException e, WebRequest webRequest){
        EmailAlreadyExistResponse exceptionResponse = new EmailAlreadyExistResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    public final ResponseEntity<Object> handlePhoneAlreadyExist(PhoneAlreadyExistException e, WebRequest webRequest){
        PhoneAlreadyExistResponse exceptionResponse = new PhoneAlreadyExistResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
