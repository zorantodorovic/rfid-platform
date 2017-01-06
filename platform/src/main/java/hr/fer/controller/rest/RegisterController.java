package hr.fer.controller.rest;

import hr.fer.model.User;
import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/rest/register")
public class RegisterController {

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody Map<String, Object> userAuthOnly,
                           HttpServletRequest request) {

        String username = (String) userAuthOnly.get("username");
        String password = (String) userAuthOnly.get("password");

        if (request.getHeader("authorization") != null) {
            return null; // already logined, deny
        }

        if (userRepository.findByUsername(username) != null) {
            return null; // user already exists, deny
        }

        return userRepository.save(new User(username, password)); // to generate userId
    }
}
