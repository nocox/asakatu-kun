package com.asakatu.controller;

import com.asakatu.OkResponse;
import com.asakatu.entity.USER_REGISTRATION_ERROR;
import com.asakatu.exeptions.UserRegistrationException;
import com.asakatu.repository.UserRepository;
import com.asakatu.entity.User;
import com.asakatu.exeptions.UserAlreadyExistException;
import com.asakatu.service.ProfileImageService;
import com.asakatu.service.UserRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRegistrationController {

    private final
    UserRepository userRepository;

    private final
    UserRegistrationService userRegistrationService;

    private final ProfileImageService profileImageService;

    public UserRegistrationController(UserRepository userRepository, UserRegistrationService userRegistrationService, ProfileImageService profileImageService) {
        this.userRegistrationService = userRegistrationService;
        this.userRepository = userRepository;
        this.profileImageService = profileImageService;
    }

    @PostMapping(path = "/user_registration")
    @ResponseBody
    public OkResponse userRegistration(@RequestBody User user) throws JsonProcessingException {

        // todo nocox 0706 nullcheckが必要かも

        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
//            throw new UserRegistrationException("ユーザ名の長さが規定の範囲と違います",USER_REGISTRATION_ERROR.USER_NAME_LENGTH_ERROR);
            //todo: ↑なんかうまく行かなかったので、一旦これで↓
            throw new IllegalArgumentException(String.valueOf(USER_REGISTRATION_ERROR.USER_NAME_LENGTH_ERROR));
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            throw new UserAlreadyExistException("そのユーザ名は既に使われています",USER_REGISTRATION_ERROR.USER_NAME_ALREADY_USED);
            throw new UserAlreadyExistException(String.valueOf(USER_REGISTRATION_ERROR.USER_NAME_ALREADY_USED));
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()){
//            throw new UserRegistrationException("そのアドレスは既に使われています",USER_REGISTRATION_ERROR.USER_NAME_ALREADY_USED);
            throw new IllegalArgumentException(String.valueOf(USER_REGISTRATION_ERROR.MAIL_ADDRESS_ALREADY_USED));

        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
//            throw new UserRegistrationException("パスワードの長さが規定の範囲と違います",USER_REGISTRATION_ERROR.PASSWORD_LENGTH_ERROR);
            throw new IllegalArgumentException(String.valueOf(USER_REGISTRATION_ERROR.PASSWORD_LENGTH_ERROR));
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
//            throw new UserRegistrationException("パスワードが一致しません",USER_REGISTRATION_ERROR.INCORRECT_PASSWORD);
            throw new IllegalArgumentException(String.valueOf(USER_REGISTRATION_ERROR.INCORRECT_PASSWORD));
        }
        user.setImagePath(profileImageService.getDefaultImagePath());
        userRegistrationService.save(user);

        return new OkResponse(new UserRegistrationResponse("success", user.getUsername()));
    }
}


class UserRegistrationResponse {
    private String message;
    private String username;

    UserRegistrationResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

}