package hr.fer.controller.web;

import hr.fer.controller.web.viewbags.UserSensorsBag;
import hr.fer.model.Record;
import hr.fer.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

//@RequestMapping("/records")
//public class RecordWebController {
//
//    private final StorageService storageService;
//
//    @Autowired
//    public RecordWebController(StorageService storageService) {
//        this.storageService = storageService;
//    }
//
//    @GetMapping
//    public String createIndex(ModelMap model) {
//        model.addAttribute("userSensorsBag", new UserSensorsBag());
//        return "record/index";
//    }
//
//    @PostMapping
//    public Iterable<Record> readRecords(ModelMap modelMap) {
//        UserSensorsBag userSensorsBag = (UserSensorsBag) modelMap.getOrDefault("userSensorsBag", null);
//        if (userSensorsBag != null) {
//            return storageService.readRecords(userSensorsBag.getUserId(),
//                    userSensorsBag.getSensorIds().stream().filter(Objects::nonNull).collect(Collectors.toList()));
//        }
//        return new ArrayList<>();
//    }
//}
