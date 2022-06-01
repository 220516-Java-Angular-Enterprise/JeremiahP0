package com.revature.P0.Utilities.custom_exceptions;

public class InvalidSQLException extends RuntimeException{
    public InvalidSQLException(String message) {
        super(message);
    }
}
