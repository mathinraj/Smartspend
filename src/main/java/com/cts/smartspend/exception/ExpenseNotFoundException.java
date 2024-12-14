package com.cts.smartspend.exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
