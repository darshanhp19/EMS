package com.maveric.ems.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maveric.ems.utils.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private long empId;
    @NotEmpty(message = "FirstName  should not be empty")
    @Size(min = 4, message = "FirstName should have at least 4 characters")
    private String employeeFirstName;

    @NotEmpty(message = "Lastname should not be empty")
    private String employeeLastName;
    @NotEmpty(message = "Email  should not be empty")
    @Email
    private String employeeEmailId;

    @JsonFormat(pattern = AppConstants.DEFAULT_DATE_FORMAT)
    private LocalDate employeeDOB;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 10, max = 10, message = "Phone number must be of 10 digits")
    private String employeePhoneNumber;
}
