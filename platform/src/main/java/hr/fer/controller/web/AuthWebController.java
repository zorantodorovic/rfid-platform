package hr.fer.controller.web;


import hr.fer.model.User;
import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class AuthWebController {

    private final UserRepository userRepository;

    @Autowired
    public AuthWebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView createRegister() {
        // src/main/resoruces/templates/register.html
        return new ModelAndView("views/register", "user", new User());
    }

    @PostMapping //ovdje mi posta login i posalje user objekt s login forme
    public ModelAndView createUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (userRepository.findByUsername(user.getUsername()) != null && result.hasErrors()) {
            return new ModelAndView("views/register", "user", new User());
        }
        userRepository.save(user);
        return new ModelAndView("views/login");
    }
}