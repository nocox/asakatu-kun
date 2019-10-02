package com.asakatu.exeptions;


import com.asakatu.entity.USER_REGISTRATION_ERROR;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class UserAlreadyExistException extends IllegalArgumentException {

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, USER_REGISTRATION_ERROR userRegistrationError) throws JsonProcessingException {
        HashMap<String,String> responseMessage = new HashMap<>();
        responseMessage.put("message",message);
        responseMessage.put("caused",userRegistrationError.toString());
        new UserAlreadyExistException(new ObjectMapper().writeValueAsString(responseMessage));
    }
}
