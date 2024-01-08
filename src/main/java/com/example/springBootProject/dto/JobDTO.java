package com.example.springBootProject.dto;

/**
 * DTO for returning job model.
 * @param id: id of a job,
 * @param name: name of a job,
 * @param description: description of a job
 */

public record JobDTO(
        Long id,
        String name,
        String description
) {
}
