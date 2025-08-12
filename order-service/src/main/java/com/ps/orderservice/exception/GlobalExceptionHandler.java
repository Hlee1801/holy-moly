package com.ps.orderservice.exception;


import com.ps.orderservice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<String> handleResourceNotFound(OrderServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
                );
        return new ResponseEntity<>(errorResponse.toString(), HttpStatus.NOT_FOUND);
    }
}
