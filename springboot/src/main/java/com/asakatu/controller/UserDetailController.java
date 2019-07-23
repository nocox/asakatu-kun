package com.asakatu.controller;

import com.asakatu.OkResponse;
import com.asakatu.entity.User;
import com.asakatu.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserDetailController {

    private final
    UserRepository userRepository;


    public UserDetailController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user/{id}")
    public OkResponse userDetail(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow();

        return okUser(user);
    }

    private OkResponse okUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> findUser = userRepository.findByUsername(authentication.getName());

        UserDetailResponse userDetailResponse = new UserDetailResponse(user);
        findUser.ifPresent(value -> userDetailResponse.setMyself(value.getId().equals(user.getId())));
        return new OkResponse(userDetailResponse);
    }
}


class UserDetailResponse {

    private String imagePath;
    private String displayName;
    private String username;
    private String email;
    private boolean myself;

    void setMyself(boolean myself) {
        this.myself = myself;
    }

    UserDetailResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
        this.imagePath = user.getImagePath();
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMyself() {
        return myself;
    }
}

