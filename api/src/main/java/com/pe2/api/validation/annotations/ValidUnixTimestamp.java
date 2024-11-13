package com.pe2.api.validation.annotations;

import com.pe2.api.validation.validators.UnixTimestampValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UnixTimestampValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUnixTimestamp {

    String message() default  "The provided timestamp must be between January 1st, 2024 and December 31st, 2030. Given timestamp is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
