package com.asakatu.controller;

import com.asakatu.OkResponse;
import com.asakatu.repository.UserRepository;
import com.asakatu.entity.User;
import com.asakatu.exeptions.UserAlreadyExistException;
import com.asakatu.service.ProfileImageService;
import com.asakatu.service.UserRegistrationService;
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
    public OkResponse userRegistration(@RequestBody User user) {

        // todo nocox 0706 nullcheckが必要かも

        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            throw new IllegalArgumentException("ユーザ名の長さが規定の範囲と違います");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("そのユーザ名は既に使われています");
        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            throw new IllegalArgumentException("パスワードの長さが規定の範囲と違います");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            throw new IllegalArgumentException("パスワードが一致しません");
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