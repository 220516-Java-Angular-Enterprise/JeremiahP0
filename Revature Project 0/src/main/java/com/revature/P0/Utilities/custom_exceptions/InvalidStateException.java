package com.revature.P0.Utilities.custom_exceptions;

public class InvalidStateException extends RuntimeException{
    public InvalidStateException(String message){
        super(message);
    }
}
