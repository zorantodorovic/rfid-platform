package hr.fer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blaa")
public class BlaaWebController {

    //TODO ovo je samo primjer kako kontroler vraca html view koji se nalazi
    //TODO u src/main/resoruces/templates/blaa/blaa.html

    @GetMapping
    String createBlaa() {
        return "blaa/blaa";
    }


}
