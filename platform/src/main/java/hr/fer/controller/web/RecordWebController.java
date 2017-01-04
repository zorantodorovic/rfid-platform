package hr.fer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hr.fer.service.StorageService;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/listRecords")
public class RecordWebController {

    private final StorageService storageService;

    @Autowired
    public RecordWebController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(method = {GET, POST})
    public String getRecords(Model model) {

        model.addAttribute("records", storageService.getAllRecords());
        return "record/record";
    }


}