package hr.fer.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/register")
public class RegisterJspController {

    @GetMapping
    public String createIndex() {
        return "register.jsp";
    }
}
