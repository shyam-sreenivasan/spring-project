package com.project.todoapp.exception_handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        if (result != null) {
            String errorMessage = result.getFieldError().getDefaultMessage();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMessage);
            return ResponseEntity.badRequest().body(responseBody);
        }
        
        return ResponseEntity.badRequest().build();
    }

}