package hr.fer.controller.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hr.fer.model.Sensor;
import hr.fer.model.SensorSink;
import hr.fer.model.Sink;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.SensorSinkRepository;
import hr.fer.repository.SinkRepository;
import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Marin on 04-Jan-17.
 */
@Controller
@RequestMapping("/sensors")
public class SensorWebController {

    private final SensorRepository sensorRepository;
    private final SensorSinkRepository sensorSinkRepository;
    private final UserRepository userRepository;
    private final SinkRepository sinkRepository;

    @Autowired
    public SensorWebController(SinkRepository sinkRepository, SensorRepository sensorRepository, SensorSinkRepository sensorSinkRepository, UserRepository userRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorSinkRepository = sensorSinkRepository;
        this.userRepository = userRepository;
        this.sinkRepository = sinkRepository;
    }

    @GetMapping
    @RequestMapping("/index")
    public ModelAndView Index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterable<Sensor> sensors = sensorRepository.findByUserId(userRepository.findByUsername(authentication.getName()).getId());
        model.addAttribute("sensors",sensors);
        Map<Sensor,Iterable<Sink>> sensorSinkMap = new HashMap<>();
        for(Sensor sensor : sensors){
            //sensorSinkRepository.
            //sensorSinkMap.put(sensor,)
        }
        return new ModelAndView("sensors/index");
    }

    @RequestMapping(value="/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterable<Sensor> userSensors = sensorRepository.findByUserId(userRepository.findByUsername(authentication.getName()).getId());
        if(StreamSupport.stream(userSensors.spliterator(),false).anyMatch(x->x.getId() == id)){
            model.addAttribute("sinks",sinkRepository.findAll());
            model.addAttribute("sink", sensorSinkRepository.findBySensorId(id));
            return new ModelAndView("sensors/edit", "sensor",sensorRepository.findOne(id));
        }else{
            return new ModelAndView("redirect:../index", "sensor",null);
        }

    }

    @RequestMapping(value="/edit/save",method = RequestMethod.POST)
    public ModelAndView save(int id, int userId, String ipAddress, String sensorType, int sink){
        Sensor sensor = sensorRepository.findOne(id);
        sensor.setUserId(userId);
        sensor.setSensorType(sensorType);
        sensor.setIpAddress(ipAddress);
        sensorRepository.save(sensor);

        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id){
        sensorRepository.delete(id);
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="add")
    public ModelAndView add(Model model){
        model.addAttribute("sensor",new Sensor());
        return new ModelAndView("sensors/add");
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute(value="sensor") Sensor sensor){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        sensor.setUserId(userRepository.findByUsername(authentication.getName()).getId());
        Iterable<Sensor> sensors = sensorRepository.findAll();
        int maxId = 0;
        for (Sensor s : sensors){
            if(s.getId() > maxId){
                maxId = s.getId();
            }
        }
        sensor.setId(maxId+1);
        sensorRepository.save(sensor);
        return new ModelAndView("redirect:index");
    }
}
