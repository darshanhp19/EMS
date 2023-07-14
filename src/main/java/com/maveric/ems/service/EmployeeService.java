package com.maveric.ems.service;

import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployee(long id);

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, long id);

    void deleteEmployee(long id);

}
