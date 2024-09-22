package com.matsugumayudi.todolist.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Exception> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        List<String> errors = List.of("Request body is required");

        return ResponseEntity.badRequest().body(new Exception(errors));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Exception> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .toList();

        return ResponseEntity.badRequest().body(new Exception(errors));
    }
}
