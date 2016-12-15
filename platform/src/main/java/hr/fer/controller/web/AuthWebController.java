package hr.fer.controller.web;


import hr.fer.model.User;
import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class AuthWebController {

    private final UserRepository repo;

    @Autowired
    public AuthWebController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ModelAndView createRegister() {
        // src/main/resoruces/templates/register.html
        return new ModelAndView("register", "user", new User());
    }

    @PostMapping
    public ModelAndView createUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (repo.findByUsername(user.getUsername()) != null && result.hasErrors()) {
            return new ModelAndView("register", "user", new User());
        }
        repo.save(user);
        return new ModelAndView("login");
    }
}