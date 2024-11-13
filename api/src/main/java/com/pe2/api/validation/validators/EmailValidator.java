package com.pe2.api.validation.validators;

import com.pe2.api.repository.AssigneeRepository;
import com.pe2.api.utils.AnnotationsUtil;
import com.pe2.api.validation.annotations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.pe2.api.utils.Constants.EMAIL_REGEX;

public class EmailValidator implements ConstraintValidator<ValidEmail,String> {
    private AssigneeRepository assigneeRepository;

    public EmailValidator(AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        // First, check if the email matches the regex
        if (!value.matches(EMAIL_REGEX)) {
            return false;  // Invalid email format
        }

        // Check if the email already exists in the database
        boolean emailExists = assigneeRepository.existsByEmail(value);
        if (emailExists) {
            // Set a custom error message
            AnnotationsUtil.setErrorMessage(constraintValidatorContext,"Email is already in use");
            return false;
        }

        return true;
    }
}
