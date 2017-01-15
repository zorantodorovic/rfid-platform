package hr.fer.controller.web;

import hr.fer.model.*;
import hr.fer.repository.*;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/chips")
public class ChipWebController {

    private final ChipRepository chipRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public ChipWebController(ChipRepository chipRepository, RecordRepository recordRepository) {
        this.chipRepository = chipRepository;
        this.recordRepository = recordRepository;
    }

    @GetMapping
    @RequestMapping("/index")
    public ModelAndView Index(){
        Iterable<Chip> chips= chipRepository.findAll();
        return new ModelAndView("chips/index","chips",chips);
    }

    @RequestMapping(value="/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") int id){
        Chip chip = chipRepository.findOne(id);
        return new ModelAndView("chips/edit", "chip",chip);
    }

    @RequestMapping(value="/edit/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(value = "chip")Chip chip){
        chipRepository.save(chip);
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, @Current CurrentUser user){
        try {
            Iterable<Record> records = recordRepository.findAll();
            for(Record r : records){
                if(r.getChipId() == id){
                    recordRepository.delete(r);
                }
            }
            chipRepository.delete(id);
        }catch (Exception e){

        }
        return new ModelAndView("redirect:../index");
    }

    @RequestMapping(value="add")
    public ModelAndView add(){
        return new ModelAndView("chips/add","chip",new Chip());
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute(value = "chip")Chip chip) {
        Iterable<Chip> chips = chipRepository.findAll();
        int maxId = 0;
        for(Chip c : chips){
            if(c.getId() > maxId)
                maxId = c.getId();
        }
        chip.setId(maxId+1);
        chipRepository.save(chip);
        return new ModelAndView("redirect:index");
    }
}