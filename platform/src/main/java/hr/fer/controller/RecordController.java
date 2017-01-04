package hr.fer.controller;

import hr.fer.model.Record;
import hr.fer.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final StorageService storageService;

    @Autowired
    public RecordController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public Iterable<Record> readRecords(@RequestBody Map<String, Object> body) {
        Integer userId = (Integer) body.getOrDefault("userId", null);
        Iterable sensorIds = (Iterable) body.getOrDefault("sensorIds", null);
        if (userId != null && sensorIds != null) {
            List<Integer> integers = new ArrayList<>();
            for (Object sensorId : sensorIds) {
                integers.add(Integer.parseInt(String.valueOf(sensorId)));
            }
            return storageService.getRecords(userId, integers);
        }
        return null;
    }

    @GetMapping
    public Iterable<Record> readAllRecords() {
        return storageService.getAllRecords();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecord(@PathVariable Integer id) {
        storageService.deleteRecords(id);
    }



}