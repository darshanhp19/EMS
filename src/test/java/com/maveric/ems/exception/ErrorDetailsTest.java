package com.maveric.ems.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class ErrorDetailsTest {

    @Test
    void testConstructorAndGetters() {
        // given
        Date timestamp = new Date();
        String message = "Error occurred";
        String details = "Invalid input";

        // when
        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        // then
        assertNotNull(errorDetails);
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    void testSetters() {
        // given
        Date timestamp = new Date();
        String message = "Error occurred";
        String details = "Invalid input";
        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        // when
        Date newTimestamp = new Date();
        String newMessage = "New error occurred";
        String newDetails = "Invalid input format";
        errorDetails.setTimestamp(newTimestamp);
        errorDetails.setMessage(newMessage);
        errorDetails.setDetails(newDetails);

        // then
        assertEquals(newTimestamp, errorDetails.getTimestamp());
        assertEquals(newMessage, errorDetails.getMessage());
        assertEquals(newDetails, errorDetails.getDetails());
    }

}

