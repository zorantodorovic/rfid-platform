package hr.fer.controller.web;

import hr.fer.model.Query;
import hr.fer.model.Sensor;
import hr.fer.model.SensorSink;
import hr.fer.model.Sink;
import hr.fer.repository.QueryRepository;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.UserRepository;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import hr.fer.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/queries")
public class QueryWebController {

    private final SensorRepository sensorRepository;
    private final QueryService queryService;
    private final QueryRepository queryRepository;

    @Autowired
    public QueryWebController(SensorRepository sensorRepository,
                              QueryService queryService, QueryRepository queryRepository) {
        this.sensorRepository = sensorRepository;
        this.queryService = queryService;
        this.queryRepository = queryRepository;
    }

    @GetMapping
    @RequestMapping("/index")
    public ModelAndView Index(@Current CurrentUser user){
        List<Integer> sensorIds = sensorRepository.findByUserId(user.getId())
                .stream().map(Sensor::getId).collect(toList());
        Iterable<Query> queries = queryService.readQueries(sensorIds);
        return new ModelAndView("queries/index","queries",queries);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, @Current CurrentUser user){
        queryService.deleteQuery(id);
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="add")
    public ModelAndView add(){
        return new ModelAndView("queries/add","query",new Query());
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute(value = "query")Query query, @Current CurrentUser user) {
        Iterable<Query> queries = queryRepository.findAll();
        int maxId = 0;
        for(Query s : queries){
            if(s.getId() > maxId)
                maxId = s.getId();
        }
        query.setId(maxId+1);
        queryService.submitQuery(query);
        return new ModelAndView("redirect:index");
    }

    @RequestMapping(value="/edit/{id}")
    //public ModelAndView edit(@PathVariable(value = "id") int id,@Current CurrentUser user){
    public ModelAndView edit(@PathVariable(value = "id") int id, @Current CurrentUser user){

        List<Integer> sensorIds = sensorRepository.findByUserId(user.getId())
                .stream().map(Sensor::getId).collect(toList());
        List<Query> sensorQueries = queryService.readQueries(sensorIds);

        if(StreamSupport.stream(sensorQueries.spliterator(),false).anyMatch(x->x.getId() == id)){
            return new ModelAndView("queries/edit", "query", queryRepository.findOne(id));
        }
        else {
            return new ModelAndView("redirect:../index", "query",null);
        }
    }

    @RequestMapping(value="/edit/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(value = "query")Query query){
        queryService.submitQuery(query);
        return new ModelAndView("redirect:../index");
    }



}