package hr.fer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userweb")
public class UserWebController {

    @GetMapping
    String createIndex() {
        return "/user/index.html";
    }
}
