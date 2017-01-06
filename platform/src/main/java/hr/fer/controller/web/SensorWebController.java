package hr.fer.controller.web;


import hr.fer.repository.SensorRepository;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/sensors")
public class SensorWebController {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorWebController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @RequestMapping(method = {GET, POST})
    public String getSensors(Model model, @Current CurrentUser user) {
        model.addAttribute("sensors", sensorRepository.findByUserId(user.getId()));
        return "views/sensor";
    }
}
