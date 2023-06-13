package com.example.preparingcv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleException (GenericException ex){
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity
                .status(ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST)
                .body(errors);

    }
}
