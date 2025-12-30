package com.example.ems.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String role;
    private Long departmentId;
}