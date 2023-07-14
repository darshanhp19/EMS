package com.maveric.ems.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmployeeFirstName("John");
        employee.setEmployeeLastName("Doe");
        employee.setEmployeeEmailId("john.doe@example.com");
        employee.setEmployeeDOB(LocalDate.of(1990, 1, 1));
        employee.setEmployeePhoneNumber("123-456-7890");
    }

    @Test
    void testEmpId() {
        assertNull(employee.getEmpId(), "EmpId should be null before persisting");
    }

    @Test
    void testEmployeeFirstName() {
        assertEquals("John", employee.getEmployeeFirstName(), "Employee first name should be John");
    }

    @Test
    void testEmployeeLastName() {
        assertEquals("Doe", employee.getEmployeeLastName(), "Employee last name should be Doe");
    }

    @Test
    void testEmployeeEmailId() {
        assertEquals("john.doe@example.com", employee.getEmployeeEmailId(), "Employee email should be john.doe@example.com");
    }

    @Test
    void testEmployeeDOB() {
        assertEquals(LocalDate.of(1990, 1, 1), employee.getEmployeeDOB(), "Employee DOB should be 1990-01-01");
    }

    @Test
    void testEmployeePhoneNumber() {
        assertEquals("123-456-7890", employee.getEmployeePhoneNumber(), "Employee phone number should be 123-456-7890");
    }
}
