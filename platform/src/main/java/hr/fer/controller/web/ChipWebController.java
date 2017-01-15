package hr.fer.controller.web;

import hr.fer.repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/chips")
public class ChipWebController {

    private final ChipRepository chipRepository;

    @Autowired
    public ChipWebController(ChipRepository chipRepository) {
        this.chipRepository = chipRepository;
    }

    @RequestMapping(method = {GET, POST})
    public String getSinks(Model model) {

        model.addAttribute("chips", chipRepository.findAll());
        return "views/chip";
    }


}