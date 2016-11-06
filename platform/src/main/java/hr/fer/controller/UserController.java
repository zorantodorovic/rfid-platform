package hr.fer.controller;


import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    @Autowired
    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String createIndex() {
        return "aloha users";
    }


}