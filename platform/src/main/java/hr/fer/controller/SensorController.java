package hr.fer.controller;


import hr.fer.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestControllerAdvice
@RequestMapping("/sensors")
public class SensorController {

    private final SensorRepository repo;

    @Autowired
    public SensorController(SensorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String createIndex() {
        return "aloha sensors";
    }
}