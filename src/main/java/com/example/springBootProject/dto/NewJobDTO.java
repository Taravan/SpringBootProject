package com.example.springBootProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * DTO for creating new job that should be mapped to job model.
 * @param name: name of a job,
 * @param description: description of a job
 */

public record NewJobDTO(

        @NotNull(message = "{job.name.notNull}")
        @NotBlank(message = "{job.name.notBlank}")
        @Pattern(regexp = "[a-zA-Z1-9]*", message = "{job.name.invalid}")
        String name,

        @NotNull(message = "{job.desc.notNull}")
        String description
) {
}
