package com.maveric.ems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.ems.dto.EmployeeDto;
import com.maveric.ems.service.serviceImplementation.EmployeeServiceImplementation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeServiceImplementation employeeService;

  private List<EmployeeDto> employeeList;


  @Before
  public void setUp() {
    employeeList = new ArrayList<>();
    employeeList.add(
        new EmployeeDto(1L, "Darshan", "Gowda", "john.doe@example.com", LocalDate.of(1999, 01, 01),
            "8660686926"));
    employeeList.add(
        new EmployeeDto(2L, "Chethan", "Gowda", "john.doe@example.com", LocalDate.of(1999, 01, 01),
            "8660686926"));

  }

  @Test
  public void testGetEmployees() throws Exception {
    Mockito.when(
            employeeService.getAllEmployees(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
        .thenReturn(employeeList);

    mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeFirstName", Matchers.is("Darshan")))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$[1].employeeFirstName", Matchers.is("Chethan")));

  }

  @Test
  public void testAddEmployee() throws Exception {
    EmployeeDto employeeDto = new EmployeeDto(1L, "Darshan", "Gowda", "john.doe@example.com",
        LocalDate.of(1999, 01, 01),
        "8660686926");

    Mockito.when(employeeService.addEmployee(Mockito.any(EmployeeDto.class)))
        .thenReturn(employeeDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/employees/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(employeeDto)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.employeeFirstName", Matchers.is("Darshan")));
  }

  @Test
  public void testGetEmployeeById() throws Exception {
    long empId = 1L;
    EmployeeDto employeeDto = new EmployeeDto(empId, "Darshan", "Gowda", "john.doe@example.com",
        LocalDate.of(1999, 01, 01),
        "8660686926");

    Mockito.when(employeeService.getEmployee(empId)).thenReturn(employeeDto);

    mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", empId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.employeeFirstName", Matchers.is("Darshan")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.employeeLastName", Matchers.is("Gowda")))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.employeePhoneNumber", Matchers.is("8660686926")));
  }
  @Test
  public void testUpdateEmployee() throws Exception {
    long empId = 1L;
    EmployeeDto employeeDto = new EmployeeDto(empId, "Darshan", "Gowda", "john.doe@example.com",
        LocalDate.of(1999, 01, 01),
        "8660686926");

    Mockito.when(employeeService.updateEmployee(Mockito.any(EmployeeDto.class), Mockito.eq(empId)))
        .thenReturn(employeeDto);

    mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", empId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(employeeDto)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.employeeFirstName", Matchers.is("Darshan")));
  }
  @Test
  public void testDeleteEmployee() throws Exception {
    long empId = 1L;

    mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", empId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Employee deleted successfully."));
    Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(empId);
  }

}