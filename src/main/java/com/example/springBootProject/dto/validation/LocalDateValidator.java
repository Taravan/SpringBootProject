package com.example.springBootProject.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 *  Class for validating inputs of LocalDate datatype.
 */

public class LocalDateValidator implements ConstraintValidator<ValidDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate max = LocalDate.now();
        LocalDate min = max.minusYears(100);

        return localDate != null && !localDate.isBefore(min) && !localDate.isAfter(max);
    }
}
