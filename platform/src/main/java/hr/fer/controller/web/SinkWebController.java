package hr.fer.controller.web;


import hr.fer.model.*;
import hr.fer.repository.ChipRepository;
import hr.fer.repository.RecordRepository;
import hr.fer.repository.SensorSinkRepository;
import hr.fer.repository.SinkRepository;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;


import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/sinks")
public class SinkWebController {

    private final SinkRepository sinkRepository;
    private final SensorSinkRepository sensorSinkRepository;

    @Autowired
    public SinkWebController(SinkRepository sinkRepository, SensorSinkRepository sensorSinkRepository) {
        this.sinkRepository = sinkRepository;
        this.sensorSinkRepository = sensorSinkRepository;
    }

    @GetMapping
    @RequestMapping("/index")
    public ModelAndView Index(@Current CurrentUser user){
        Iterable<Sink> sinks= sinkRepository.findByUserId(user.getId());
        return new ModelAndView("sinks/index","sinks",sinks);
    }

    @RequestMapping(value="/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") int id,@Current CurrentUser user){
        Sink sink = sinkRepository.findOne(id);
        List<Sink> userSinks = sinkRepository.findByUserId(user.getId());
        if(StreamSupport.stream(userSinks.spliterator(),false).anyMatch(x->x.getId() == id)){
            return new ModelAndView("sinks/edit", "sink",sink);
        }
        else {
            return new ModelAndView("redirect:../index", "sink",null);
        }
    }

    @RequestMapping(value="/edit/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(value = "sink")Sink sink){
        sinkRepository.save(sink);
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, @Current CurrentUser user){
        Sink sink = sinkRepository.findOne(id);
        List<Sink> userSinks = sinkRepository.findByUserId(user.getId());
        if(StreamSupport.stream(userSinks.spliterator(),false).anyMatch(x->x.getId() == id)){
            Iterable<SensorSink> sensorSink = sensorSinkRepository.findAll();
            for(SensorSink ss : sensorSink){
                if(ss.getSinkId() == id){
                    sensorSinkRepository.delete(ss);
                }
            }
            sinkRepository.delete(sink);
        }
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="add")
    public ModelAndView add(){
        return new ModelAndView("sinks/add","sink",new Sink());
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute(value = "sink")Sink sink, @Current CurrentUser user) {
        Iterable<Sink> sinks = sinkRepository.findAll();
        int maxId = 0;
        for(Sink s : sinks){
            if(s.getId() > maxId)
                maxId = s.getId();
        }
        sink.setId(maxId+1);
        sink.setUserId(user.getId());
        sinkRepository.save(sink);
        return new ModelAndView("redirect:index");
    }

}