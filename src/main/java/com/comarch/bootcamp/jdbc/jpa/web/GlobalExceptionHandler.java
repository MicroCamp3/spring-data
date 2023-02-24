package com.comarch.bootcamp.jdbc.jpa.web;

import com.comarch.bootcamp.jdbc.jpa.dto.ValidationErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationErrors> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errorsList = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(ValidationErrors.builder().errors(errorsList).build());
    }
}
