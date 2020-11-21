package com.sda.weather;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}
