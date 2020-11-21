package com.sda.weather;

public class NotFoundException extends RuntimeException {

    // todo create ExceptionHandler
    public NotFoundException(String message){
        super(message);
    }
}
