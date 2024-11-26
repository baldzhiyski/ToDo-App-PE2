package com.pe2.api.restController;

import com.pe2.api.exceptions.InvalidEmailException;
import com.pe2.api.exceptions.NoSuchAssigneeException;
import com.pe2.api.exceptions.NoSuchToDoException;
import com.pe2.api.utils.HttpErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<String> generalErrors = new ArrayList<>();

        // Iterate through the binding result to extract field-specific and general errors.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldErr) {
                String fieldName = fieldErr.getField();
                String errorMessage = fieldErr.getDefaultMessage();
                // Map the field name to its corresponding error message.
                errors.put(fieldName, errorMessage);
            } else {
                // Collect general errors that do not pertain to a specific field.
                generalErrors.add(error.getDefaultMessage());
            }
        });

        // Create a structured error response for validation errors.
        HttpErrorResponse response = HttpErrorResponse.of("Unprocessable entity", 400, errors, generalErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchAssigneeException.class)
    public ResponseEntity<String> handleNoSuchAssigneeException(NoSuchAssigneeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchToDoException.class)
    public ResponseEntity<String> handleNoSuchToDoException(NoSuchToDoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleNoSuchToDoException(InvalidEmailException ex) {
        HttpErrorResponse httpErrorResponse = HttpErrorResponse.of(ex.getMessage(), 400);
        return new ResponseEntity<>(httpErrorResponse,HttpStatus.BAD_REQUEST);
    }
}