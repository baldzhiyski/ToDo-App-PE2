package com.pe2.api.validation.validators;

import com.pe2.api.validation.annotations.ValidUnixTimestamp;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.time.Instant;


public class UnixTimestampValidator implements ConstraintValidator<ValidUnixTimestamp, Long> {

    @Override
    public void initialize(ValidUnixTimestamp constraintAnnotation) {
        // Initialization logic, if needed (not necessary in this case)
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Unix timestamp for January 1st, 2024
        long startOf2024 = Instant.parse("2024-01-01T00:00:00Z").toEpochMilli();
        // Unix timestamp for December 31st, 2030 (end of the year)
        long endOf2030 = Instant.parse("2030-12-31T23:59:59.999Z").toEpochMilli();

        // Check if the value is within the range
        return value >= startOf2024 && value <= endOf2030;
    }
}
