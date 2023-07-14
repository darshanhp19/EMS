package com.maveric.ems.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.entity.Employee;
import com.maveric.ems.mapper.EmployeeMapper;
import com.maveric.ems.repository.EmployeeRepository;
import com.maveric.ems.service.serviceImplementation.EmployeeServiceImplementation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceImplementationTest {

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeServiceImplementation employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImplementation();
        employeeService.employeeMapper = employeeMapper;
        employeeService.employeeRepository = employeeRepository;
    }

    @Test
    void testGetEmployee() {
        long employeeId = 1L;
        Employee employee = new Employee();
        employee.setEmpId(employeeId);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpId(employeeId);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDto);
        EmployeeDto result = employeeService.getEmployee(employeeId);
        assertEquals(employeeDto.getEmpId(), result.getEmpId());
    }

    @Test
    void testAddEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeMapper.toEntity(employeeDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toDTO(employee)).thenReturn(employeeDto);
        EmployeeDto result = employeeService.addEmployee(employeeDto);
        assertEquals(employeeDto, result);
    }

    @Test
    void testFindAllEmployees() {
        int pageNumber = 0;
        int pageSize = 10;
        String sortBy = "employeeFirstName";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Darshan", "Gowda", "john.doe@example.com", LocalDate.of(1999, 01, 01),
            "8660686926"));
        employees.add(new Employee(2L, "Chethan", "Gowda", "john.doe@example.com", LocalDate.of(1999, 01, 01),
            "8660686926"));
        List<EmployeeDto> DtoEmployees = employees.stream()
            .map(employee -> employeeMapper.toDTO(employee))
            .collect(Collectors.toList());


        Page<Employee> pagedResult = new PageImpl<>(employees);

        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(pagedResult);
        List<EmployeeDto> result = employeeService.getAllEmployees(pageNumber, pageSize, sortBy);

        assertEquals(DtoEmployees, result);
    }

    @Test
    void testUpdateEmployee() {
        long employeeId = 1L;
        Employee employee = new Employee();
        employee.setEmpId(employeeId);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpId(employeeId);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDto);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto result = employeeService.updateEmployee(employeeDto, employeeId);
        assertEquals(employeeDto, result);
    }

    @Test
    void testDeleteEmployee() {
        long employeeId = 1L;
        Employee employee = new Employee();
        employee.setEmpId(employeeId);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(employeeId);
        assertTrue(employeeRepository.findById(employeeId).isPresent());
    }

}
