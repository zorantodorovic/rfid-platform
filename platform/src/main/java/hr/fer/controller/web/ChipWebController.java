package hr.fer.controller.web;

import hr.fer.controller.SinkController;
import hr.fer.repository.ChipRepository;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hr.fer.service.StorageService;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.stream.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/chips/index")
public class ChipWebController {

    private final ChipRepository chipRepository;

    @Autowired
    public ChipWebController(ChipRepository chipRepository) {
        this.chipRepository = chipRepository;
    }

    @RequestMapping(method = {GET, POST})
    public String getSinks(Model model) {

        model.addAttribute("chips", chipRepository.findAll());
        return "chip/chip";
    }


}