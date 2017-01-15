package hr.fer.controller.web;

import hr.fer.model.Sensor;
import hr.fer.model.SensorSink;
import hr.fer.model.Sink;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.SensorSinkRepository;
import hr.fer.repository.SinkRepository;
import hr.fer.repository.UserRepository;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * Created by Marin on 04-Jan-17.
 */
@Controller
@RequestMapping("/sensors")
public class SensorWebController {

    private final SensorRepository sensorRepository;
    private final SensorSinkRepository sensorSinkRepository;
    private final SinkRepository sinkRepository;

    @Autowired
    public SensorWebController(SinkRepository sinkRepository, SensorRepository sensorRepository, SensorSinkRepository sensorSinkRepository, UserRepository userRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorSinkRepository = sensorSinkRepository;
        this.sinkRepository = sinkRepository;
    }

    @GetMapping
    @RequestMapping("/index")
    public ModelAndView Index(Model model, @Current CurrentUser user){
        Iterable<Sensor> sensors = sensorRepository.findByUserId(user.getId());
        Map<Sensor,List<Sink>> sensorIterableMap = new HashMap<>();
        for(Sensor sensor : sensors){
            Iterable<SensorSink> ss = sensorSinkRepository.findBySensorId(sensor.getId());
            List<Sink> sinks = new LinkedList<>();
            for(SensorSink sensorSink : ss){
                sinks.add(sinkRepository.findOne(sensorSink.getSinkId()));
            }
            sensorIterableMap.put(sensor,sinks);
        }
        return new ModelAndView("sensors/index","sensors",sensorIterableMap);
    }

    @RequestMapping(value="/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") int id, Model model, @Current CurrentUser user){
        Iterable<Sensor> userSensors = sensorRepository.findByUserId(user.getId());
        if(StreamSupport.stream(userSensors.spliterator(),false).anyMatch(x->x.getId() == id)){
            Iterable<Sink> sinks = sinkRepository.findByUserId(user.getId());
            Iterable<SensorSink> sensorSinks = sensorSinkRepository.findBySensorId(id);
            Map<Sink,Boolean> sink = new HashMap<Sink,Boolean>();
            for(Sink s : sinks){
                if(StreamSupport.stream(sensorSinks.spliterator(),false).anyMatch(x->x.getSinkId()==s.getId())){
                    sink.put(s,true);
                }else{
                    sink.put(s,false);
                }
            }
            model.addAttribute("sinks",sink);
            return new ModelAndView("sensors/edit", "sensor",sensorRepository.findOne(id));
        }else{
            return new ModelAndView("redirect:../index", "sensor",null);
        }

    }

    @RequestMapping(value="/edit/save",method = RequestMethod.POST)
    public ModelAndView save(int id, int userId, String ipAddress, String sensorType, int[] sinks){
        Sensor sensor = sensorRepository.findOne(id);
        sensor.setUserId(userId);
        sensor.setSensorType(sensorType);
        sensor.setIpAddress(ipAddress);
        sensorRepository.save(sensor);
        sensorSinkRepository.delete(sensorSinkRepository.findBySensorId(id));
        for(int i = 0; i < sinks.length; i++){
            SensorSink ss = new SensorSink();
            ss.setSensorId(id);
            ss.setSinkId(sinks[i]);
            sensorSinkRepository.save(ss);
        }
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, @Current CurrentUser user){
        Iterable<Sensor> userSensors = sensorRepository.findByUserId(user.getId());
        if(StreamSupport.stream(userSensors.spliterator(),false).anyMatch(x->x.getId() == id)){
            sensorSinkRepository.delete(sensorSinkRepository.findBySensorId(id));
            sensorRepository.delete(id);
        }
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="add")
    public ModelAndView add(Model model, @Current CurrentUser user){
        model.addAttribute("sinks",sinkRepository.findByUserId(user.getId()));
        return new ModelAndView("sensors/add");
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView add(String ipAddress, String sensorType, int[] sinks, @Current CurrentUser user){
        Sensor sensor = new Sensor();
        sensor.setUserId(user.getId());
        Iterable<Sensor> sensors = sensorRepository.findAll();
        int maxId = 0;
        for (Sensor s : sensors){
            if(s.getId() > maxId){
                maxId = s.getId();
            }
        }
        sensor.setId(maxId+1);
        sensor.setIpAddress(ipAddress);
        sensor.setSensorType(sensorType);
        sensorRepository.save(sensor);
        sensors = sensorRepository.findAll();
        for(int i = 0; i < sinks.length; i++){
            SensorSink ss = new SensorSink();
            ss.setSensorId(sensor.getId());
            ss.setSinkId(sinks[i]);
            sensorSinkRepository.save(ss);
        }
        return new ModelAndView("redirect:index");
    }
}
