package com.example.springBootProject.dto;

import com.example.springBootProject.model.Job;

/**
 * DTO for returning employee model.
 * @param id: id of employee,
 * @param firstName: first name of employee,
 * @param lastName: last name of employee,
 * @param age: age of employee,
 * @param telNum: employee's telephone number,
 * @param job: employee's job
 */

public record EmployeeDTO(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        String telNum,
        Job job
) {
}
