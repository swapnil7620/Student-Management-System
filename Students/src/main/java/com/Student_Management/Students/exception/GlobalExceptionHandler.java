package com.Student_Management.Students.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDuplicate(DataIntegrityViolationException ex) {

        Map<String, String> error = new HashMap<>();

        String message = ex.getMostSpecificCause().getMessage();

        if (message != null) {

            // Division code duplicate
            if (message.contains("code")) {
                error.put("code", "Division code already exists");
            }

            // Student email duplicate
            else if (message.contains("email")) {
                error.put("email", "Email already exists");
            }

            // Student phone duplicate
            else if (message.contains("phone")) {
                error.put("phone", "Phone already exists");
            }

            // Standard duplicate
            else if (message.contains("standard")) {
                error.put("standard", "Standard already exist ");
            }

            else {
                error.put("error", "Duplicate data found");
            }

        } else {
            error.put("error", "Duplicate data found");
        }

        return ResponseEntity.badRequest().body(error);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseError(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                "Invalid input type. Please check your request body.",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBusiness(IllegalArgumentException ex) {

        return ResponseEntity.badRequest()
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Something went wrong "));
    }
}
