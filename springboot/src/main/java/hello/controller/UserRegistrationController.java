package hello.controller;

import hello.entity.User;
import hello.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final
    UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/registration")
    public User registration(@RequestBody User user) {
        // TODO: nocox 0706 ここでバリデーション
        return userRepository.save(user);
    }
}
