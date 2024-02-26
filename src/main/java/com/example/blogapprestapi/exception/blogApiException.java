package com.example.blogapprestapi.exception;

import org.springframework.http.HttpStatus;

//As for of now ----> didn't use anywhere
public class blogApiException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public blogApiException(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public blogApiException(HttpStatus httpStatus, String message, String message1){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
