package com.maveric.ems.service.serviceImplementation;

import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.entity.Employee;
import com.maveric.ems.exception.ResourceNotFoundException;
import com.maveric.ems.mapper.EmployeeMapper;
import com.maveric.ems.repository.EmployeeRepository;
import com.maveric.ems.service.EmployeeService;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    public EmployeeMapper employeeMapper;
    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getEmployee(long id) {
        log.info("Getting employee with id: ", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return employeeMapper.toDTO(employee);
    }


    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        log.info("Creating employee: ", employeeDto);
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Created employee: ", employeeDto);
        return employeeMapper.toDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        log.info("Getting all employees");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> pagedResult = employeeRepository.findAll(paging);
        List<EmployeeDto> newResult = pagedResult.getContent().stream()
            .map(employee -> employeeMapper.toDTO(employee))
            .collect(Collectors.toList());

        if(pagedResult.hasContent()) {
            return newResult;
        } else {
            return new ArrayList<EmployeeDto>();
        }
    }
    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDetail, long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        log.info("Updating employee: {}", employee);

        employee.setEmployeeFirstName(employeeDetail.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeDetail.getEmployeeLastName());
        employee.setEmployeeEmailId(employeeDetail.getEmployeeEmailId());
        employee.setEmployeeDOB(employeeDetail.getEmployeeDOB());
        employee.setEmployeePhoneNumber(employeeDetail.getEmployeePhoneNumber());

        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Updated employee: {}", employee);
        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        log.info("Deleting employee: {}", employee);
        employeeRepository.delete(employee);

    }


}
