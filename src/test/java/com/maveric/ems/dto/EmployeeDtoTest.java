package com.maveric.ems.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidEmployeeDto() {
        EmployeeDto employee = new EmployeeDto(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "1234567890");
        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employee);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidEmployeeDto() {
        EmployeeDto employee = new EmployeeDto(1L, "", "", "not_an_email", null, "123");
        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employee);
        assertEquals(5, violations.size());
    }

    @Test
    void testFirstNameTooShort() {
        EmployeeDto employee = new EmployeeDto(1L, "Bob", "Smith", "bob.smith@example.com", LocalDate.of(1980, 1, 1), "1234567890");
        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employee);
        ConstraintViolation<EmployeeDto> violation = violations.iterator().next();
        assertEquals("FirstName should have at least 4 characters", violation.getMessage());
    }

    @Test
    void testInvalidLastName() {
        EmployeeDto employeeDto = new EmployeeDto(1L, "John", "", "john.doe@example.com", LocalDate.now(), "1234567890");
        assertEquals(1, validator.validate(employeeDto).size());
    }

    @Test
    void testInvalidEmail() {
        EmployeeDto employeeDto = new EmployeeDto(1L, "John", "Doe", "invalid-email", LocalDate.now(), "1234567890");
        assertEquals(1, validator.validate(employeeDto).size());
    }

    @Test
    void testPhoneNumberTooLong() {
        EmployeeDto employee = new EmployeeDto(1L, "Alice", "Jones", "alice.jones@example.com", LocalDate.of(1970, 1, 1), "12345678901");
        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employee);
        ConstraintViolation<EmployeeDto> violation = violations.iterator().next();
        assertEquals("Phone number must be of 10 digits", violation.getMessage());
    }

}
