package com.maveric.ems.mapper;

import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeDto toDTO(Employee employee) {
        EmployeeDto employeeDTO = new EmployeeDto();
        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setEmployeeFirstName(employee.getEmployeeFirstName());
        employeeDTO.setEmployeeLastName(employee.getEmployeeLastName());
        employeeDTO.setEmployeeEmailId(employee.getEmployeeEmailId());
        employeeDTO.setEmployeeDOB(employee.getEmployeeDOB());
        employeeDTO.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
        return employeeDTO;
    }

    public Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeFirstName(employeeDto.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeDto.getEmployeeLastName());
        employee.setEmployeeEmailId(employeeDto.getEmployeeEmailId());
        employee.setEmployeeDOB(employeeDto.getEmployeeDOB());
        employee.setEmployeePhoneNumber(employeeDto.getEmployeePhoneNumber());
        return employee;
    }
}
