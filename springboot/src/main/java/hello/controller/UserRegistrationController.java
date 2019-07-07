package hello.controller;

import hello.entity.User;
import hello.repository.UserRepository;
import hello.service.UserRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@RestController
public class UserRegistrationController {

    private final
    UserRepository userRepository;

    private final
    UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRepository userRepository, UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/registration")
    @ResponseBody
    public CreateUserResponse registration(@RequestBody User user) {

        // todo nocox 0706 nullcheckが必要かも

        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            throw new IllegalArgumentException("ユーザ名の長さが規定の範囲と違います");
        }

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("そのユーザ名は既に使われています．");
        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            throw new IllegalArgumentException("パスワードの長さが規定の範囲と違います");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            throw new IllegalArgumentException("パスワードが一致しません．");
        }


        userRegistrationService.save(user);
        return new CreateUserResponse("success", user.getUsername());
    }
}

class CreateUserResponse  {
    private String message;
    private String username;

    public CreateUserResponse(String message, String username) {
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