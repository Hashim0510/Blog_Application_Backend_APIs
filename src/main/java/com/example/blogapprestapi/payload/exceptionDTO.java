package com.example.blogapprestapi.payload;

import java.util.Date;

//dto class is used to send the
public class exceptionDTO {

    private Date timestamp;
    private String message;
    private String details;

    public exceptionDTO(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
