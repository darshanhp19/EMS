package com.maveric.ems.mapper;

import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class EmployeeMapperTest {

    private EmployeeMapper employeeMapper;

    @BeforeEach
    public void setUp() {
        employeeMapper = new EmployeeMapper();
    }

    @Test
    void testToDto() {
        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmployeeFirstName("John");
        employee.setEmployeeLastName("Doe");
        employee.setEmployeeEmailId("johndoe@example.com");
        employee.setEmployeeDOB(LocalDate.of(1990, 1, 1));
        employee.setEmployeePhoneNumber("1234567890");

        EmployeeDto employeeDto = employeeMapper.toDTO(employee);

        Assertions.assertEquals(1L, employeeDto.getEmpId());
        Assertions.assertEquals("John", employeeDto.getEmployeeFirstName());
        Assertions.assertEquals("Doe", employeeDto.getEmployeeLastName());
        Assertions.assertEquals("johndoe@example.com", employeeDto.getEmployeeEmailId());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), employeeDto.getEmployeeDOB());
        Assertions.assertEquals("1234567890", employeeDto.getEmployeePhoneNumber());
    }

    @Test
    void testToEntity() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeFirstName("John");
        employeeDto.setEmployeeLastName("Doe");
        employeeDto.setEmployeeEmailId("johndoe@example.com");
        employeeDto.setEmployeeDOB(LocalDate.of(1990, 1, 1));
        employeeDto.setEmployeePhoneNumber("1234567890");

        Employee employee = employeeMapper.toEntity(employeeDto);

        Assertions.assertEquals("John", employee.getEmployeeFirstName());
        Assertions.assertEquals("Doe", employee.getEmployeeLastName());
        Assertions.assertEquals("johndoe@example.com", employee.getEmployeeEmailId());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), employee.getEmployeeDOB());
        Assertions.assertEquals("1234567890", employee.getEmployeePhoneNumber());
    }
}