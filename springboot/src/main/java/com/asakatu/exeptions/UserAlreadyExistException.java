package com.asakatu.exeptions;

public class UserAlreadyExistException extends IllegalArgumentException {

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
