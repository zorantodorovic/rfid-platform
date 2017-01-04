package hr.fer.controller.web;

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
@RequestMapping("/records/index")
public class RecordWebController {

    private final StorageService storageService;
    private final UserRepository userRepository;
    private final SensorRepository sensorRepository;
    private int userId;

    @Autowired
    public RecordWebController(StorageService storageService, UserRepository userRepository, SensorRepository sensorRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.sensorRepository = sensorRepository;

    }

    @RequestMapping(method = {GET, POST})
    public String getRecords(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = userRepository.findByUsername(auth.getName()).getId();
        List<Integer> sensorIds = StreamSupport.stream(sensorRepository.findByUserId(userId).spliterator(), false)
                .map(map -> map.getId())
                .collect(Collectors.toList());

        model.addAttribute("records", storageService.getRecords(userId, sensorIds));
        return "record/record";
    }


}