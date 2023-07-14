package com.maveric.ems.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeAPIExceptionTest {

    @Test
    void testConstructorWithStatusAndMessage() {
        // given
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Invalid request";

        // when
        EmployeeAPIException exception = new EmployeeAPIException(status, message);

        // then
        assertNotNull(exception);
        assertEquals(status, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndStatusAndDetailMessage() {
        // given
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal server error";
        String detailMessage = "Something went wrong";

        // when
        EmployeeAPIException exception = new EmployeeAPIException(message, status, detailMessage);

        // then
        assertNotNull(exception);
        assertEquals(status, exception.getStatus());
        assertEquals(detailMessage, exception.getMessage());
    }

    @Test
    void testGetStatus() {
        // given
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Employee not found";
        EmployeeAPIException exception = new EmployeeAPIException(status, message);

        // when
        HttpStatus result = exception.getStatus();

        // then
        assertEquals(status, result);
    }

    @Test
    void testGetMessage() {
        // given
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Unauthorized access";
        EmployeeAPIException exception = new EmployeeAPIException(status, message);

        // when
        String result = exception.getMessage();

        // then
        assertEquals(message, result);
    }

}
