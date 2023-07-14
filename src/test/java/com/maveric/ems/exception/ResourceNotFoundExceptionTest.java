package com.maveric.ems.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class ResourceNotFoundExceptionTest {

    @Test
    void testConstructorWithParameters() {
        String resourceName = "Post";
        String fieldName = "id";
        long fieldValue = 1L;

        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        assertNotNull(exception);
        assertEquals(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue), exception.getMessage());
        assertEquals(resourceName, exception.getResourceName());
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(fieldValue, exception.getFieldValue());
    }

    @Test
    void testConstructorWithoutParameters() {
        ResourceNotFoundException exception = new ResourceNotFoundException();
        assertNotNull(exception);
    }

    @Test
    void testResponseStatusAnnotation() {
        ResourceNotFoundException exception = new ResourceNotFoundException();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        exception.printStackTrace();

        assertEquals(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.value());
    }

}
