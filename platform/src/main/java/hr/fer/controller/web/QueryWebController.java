package hr.fer.controller.web;

import hr.fer.model.Sensor;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.UserRepository;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import hr.fer.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/queries")
public class QueryWebController {

    private final SensorRepository sensorRepository;
    private final QueryService queryService;

    @Autowired
    public QueryWebController(SensorRepository sensorRepository,
                              QueryService queryService) {
        this.sensorRepository = sensorRepository;
        this.queryService = queryService;
    }

    @RequestMapping(method = {GET, POST})
    public String getQueries(Model model, @Current CurrentUser user) {

        List<Integer> sensorIds = sensorRepository.findByUserId(user.getId())
                .stream().map(Sensor::getId).collect(toList());

        model.addAttribute("queries", queryService.readQueries(sensorIds));

        return "views/query";
    }


}