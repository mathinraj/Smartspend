package com.cts.smartspend.exception;

import com.cts.smartspend.controller.BudgetController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<Object> handleBudgetNotFoundException(BudgetNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return buildErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return buildErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Object> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return buildErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return buildErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status.value());
        return new ResponseEntity<>(response, status);
    }
}
