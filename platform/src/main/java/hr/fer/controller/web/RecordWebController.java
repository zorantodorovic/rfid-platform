package hr.fer.controller.web;

import hr.fer.model.Sensor;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.UserRepository;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hr.fer.service.RecordService;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/records")
public class RecordWebController {

    private final RecordService recordService;
    private final SensorRepository sensorRepository;

    @Autowired
    public RecordWebController(RecordService recordService,
                               SensorRepository sensorRepository) {
        this.recordService = recordService;
        this.sensorRepository = sensorRepository;

    }

    @RequestMapping(method = {GET, POST})
    public String getRecords(Model model, @Current CurrentUser user) {

        List<Integer> sensorIds = sensorRepository.findByUserId(user.getId())
                .stream().map(Sensor::getId).collect(toList());

        model.addAttribute("records", recordService.readRecords(sensorIds));

        return "views/record";
    }


}