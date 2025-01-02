package com.cts.smartspend.exception;

public class OverlappingBudgetException extends RuntimeException{
    public OverlappingBudgetException(String message){
        super(message);
    }
}
