package com.asakatu.exeptions;

import com.asakatu.entity.USER_REGISTRATION_ERROR;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class UserRegistrationException extends IllegalArgumentException{

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, USER_REGISTRATION_ERROR userRegistrationError) throws JsonProcessingException {
        HashMap<String,String> responseMessage = new HashMap<>();
        responseMessage.put("message",message);
        responseMessage.put("caused",userRegistrationError.toString());
        new UserRegistrationException(new ObjectMapper().writeValueAsString(responseMessage));

    }
}
