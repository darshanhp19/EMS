package com.maveric.ems.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("empId")
    private Long empId;

    @Column(name = "employee_firstname", nullable = false)
    private String employeeFirstName;

    @Column(name = "employee_lastname", nullable = false)
    private String employeeLastName;

    @Column(name = "employee_email_id", nullable = false)
    private String employeeEmailId;

    @Column(name = "employee_dob", nullable = false)
    private LocalDate employeeDOB;

    @Column(name = "employee_phone_number", nullable = false)
    private String employeePhoneNumber;

}
