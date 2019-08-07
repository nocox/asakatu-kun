package com.asakatu.controller;

import com.asakatu.OkResponse;
import com.asakatu.entity.User;
import com.asakatu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginInfoController {

    private final
    UserRepository userRepository;

    public LoginInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login_user")
    public OkResponse getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> findUser = userRepository.findByUsername(authentication.getName());

        return new OkResponse(new SimpleUserResponse(findUser.orElseThrow()));
    }
}

class SimpleUserResponse{
    private String username;
    private String imagePath;
    private String displayName;

    public SimpleUserResponse(User user) {
        this.username = user.getUsername();
        this.imagePath = user.getImagePath();
        this.displayName = user.getDisplayName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}