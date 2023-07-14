package com.maveric.ems.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class EmployeeAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public EmployeeAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public EmployeeAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
