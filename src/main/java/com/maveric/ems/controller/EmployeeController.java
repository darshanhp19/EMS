package com.maveric.ems.controller;

import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.service.serviceImplementation.EmployeeServiceImplementation;
import com.maveric.ems.utils.AppConstants;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeServiceImplementation service;


  @GetMapping()
  public List<EmployeeDto> getEmployees(
      @RequestParam(defaultValue = "${default.pageNo}") Integer pageNo,
      @RequestParam(defaultValue = "${default.pageSize}") Integer pageSize,
      @RequestParam(defaultValue = "${default.sortBy}") String sortBy) {
    List<EmployeeDto> list = service.getAllEmployees(pageNo, pageSize, sortBy);
    return list;
  }


  @PostMapping("/add")
  public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    return new ResponseEntity<>(service.addEmployee(employeeDto), HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id) {
    return ResponseEntity.ok(service.getEmployee(id));
  }

  @PutMapping("{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,
      @PathVariable("id") long id) {
    final EmployeeDto updatedUser = service.updateEmployee(employeeDto, id);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);

  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
    service.deleteEmployee(id);
    return new ResponseEntity<>(AppConstants.DEL_MSG, HttpStatus.OK);
  }
}
