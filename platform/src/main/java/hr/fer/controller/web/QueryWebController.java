package hr.fer.controller.web;

import hr.fer.controller.SinkController;
import hr.fer.repository.QueryRepository;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.SinkRepository;
import hr.fer.repository.UserRepository;
import hr.fer.service.QueryService;
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
@RequestMapping("/queries/index")
public class QueryWebController {

    private final QueryRepository queryRepository;
    private final UserRepository userRepository;
    private final SensorRepository sensorRepository;
    private final QueryService queryService;
    private int userId;

    @Autowired
    public QueryWebController(QueryRepository queryRepository, UserRepository userRepository, SensorRepository sensorRepository, QueryService queryService) {
        this.queryRepository = queryRepository;
        this.userRepository = userRepository;
        this.sensorRepository = sensorRepository;
        this.queryService = queryService;
    }

    @RequestMapping(method = {GET, POST})
    public String getQueries(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = userRepository.findByUsername(auth.getName()).getId();
        List<Integer> sensorIds = StreamSupport.stream(sensorRepository.findByUserId(userId).spliterator(), false)
                .map(map -> map.getId())
                .collect(Collectors.toList());

        //TODO: napraviti getqueries metodu u queryService koja trazi querye od liste senzora, ne zelimo vracati sve querye kao sada
        model.addAttribute("queries", queryService.readQueries());
        return "query/query";
    }


}