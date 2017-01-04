package hr.fer.controller.web;


import hr.fer.repository.SinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/sinks/index")
public class SinkWebController {

    private final SinkRepository sinkRepository;

    @Autowired
    public SinkWebController(SinkRepository sinkRepository) {
        this.sinkRepository = sinkRepository;
    }

    @RequestMapping(method = {GET, POST})
    public String getSinks(Model model) {

        model.addAttribute("sinks", sinkRepository.findAll());
        return "sink/sink";
    }


}