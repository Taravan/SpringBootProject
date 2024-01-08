package com.example.springBootProject.dto;

import com.example.springBootProject.dto.validation.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/**
 * DTO for updating new employee that should be mapped to employee model.
 * @param firstName: first name of employee,
 * @param lastName: last name of employee,
 * @param dob: date of birth,
 * @param telNum: employee's telephone number,
 * @param jobId: job id of employee's job
 */

public record UpdateEmployeeDTO(
        @NotNull(message = "{employee.firstName.notNull}")
        @NotBlank(message = "{employee.firstName.notBlank}")
        @Pattern(regexp = "[a-zA-Z]*", message = "{employee.firstName.invalid}")
        String firstName,

        @NotNull(message = "{employee.lastName.notNull}")
        @NotBlank(message = "{employee.lastName.notBlank}")
        @Pattern(regexp = "[a-zA-Z]*", message = "{employee.lastName.invalid}")
        String lastName,

        @ValidDate(message = "{employee.dob.invalid}")
        LocalDate dob,

        @NotNull(message = "{employee.telNum.notNull}")
        @Pattern(regexp = "[0-9]{9}", message = "{employee.telNum.invalid}")
        String telNum,

        @NotNull(message = "{employee.jobId.notNull}")
        @Positive(message = "{employee.jobId.invalid}")
        Long jobId
) {
}
