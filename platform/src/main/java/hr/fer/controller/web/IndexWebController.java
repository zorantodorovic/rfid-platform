package hr.fer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/")
public class IndexWebController {

    // "/login"  redirects after success to "/" with POST
    @RequestMapping(method = {GET, POST})
    public String createIndex() {
        return "views/index";
    }


}