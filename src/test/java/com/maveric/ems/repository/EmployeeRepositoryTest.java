package com.maveric.ems.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.maveric.ems.entity.Employee;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindById() {
        Employee employee = employeeRepository.findById(22L).orElse(null);
        assertThat(employee).isNotNull();
        assertThat("Vinay").isEqualTo(employee.getEmployeeFirstName());
    }

    @Test
    void testFindAll() {
        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).isNotNull();
        assertTrue(employees.stream().anyMatch(e -> e.getEmployeeFirstName().equals("Suhas")));
        assertTrue(employees.stream().anyMatch(e -> e.getEmployeePhoneNumber().equals("8660689296")));
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = employeeRepository.findById(8L).orElse(null);
        employeeRepository.delete(employee);
        assertFalse(employeeRepository.findById(employee.getEmpId()).isPresent());
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee(19L, "sagar", "kumar", "sagar@gmail.com", LocalDate.of(2004, 12, 10), "9066656812");

        // when
        employeeRepository.save(employee);

        // then
        assertNotNull(employee.getEmpId());
        assertEquals("sagar", employee.getEmployeeFirstName());
        assertEquals("9066656812", employee.getEmployeePhoneNumber());
    }
}