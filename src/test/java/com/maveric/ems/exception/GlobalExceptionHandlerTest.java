package com.maveric.ems.exception;

import com.maveric.ems.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleResourceNotFoundException() {
        Employee employee = new Employee(19L, "sagar", "kumar", "sagar@gmail.com", LocalDate.of(2004, 12, 10), "9066656812");
        ResourceNotFoundException exception = new ResourceNotFoundException("Employee", "id", employee.getEmpId());
        WebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handleResourceNotFoundException(
                exception, webRequest);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(exception.getMessage(), responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleEmployeeAPIException() {
        EmployeeAPIException exception = new EmployeeAPIException(HttpStatus.BAD_REQUEST
                , "Invalid employee data");
        WebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handleEmployeeAPIException(exception,
                webRequest);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid employee data", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleGlobalException() {
        Exception exception = new Exception("Internal server error");
        WebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handleGlobalException(exception, webRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error", responseEntity.getBody().getMessage());
    }
}
//    @Test
//    public void testHandleValidationExceptions() {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("name", "Name is required");
//        errors.put("age", "Age should be between 18 and 60");
//        FieldError fieldError1 = new FieldError("employee", "name", "Name is required");
//        FieldError fieldError2 = new FieldError("employee", "age", "Age should be between 18 and 60");
//        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
//        when(exception.getBindingResult().getAllErrors()).thenReturn(List.of(fieldError1, fieldError2));
//        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleValidationExceptions(exception);
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(errors, responseEntity.getBody());
//    }
//}
