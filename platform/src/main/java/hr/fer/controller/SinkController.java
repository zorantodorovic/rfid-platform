package hr.fer.controller;


import hr.fer.repository.SinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestControllerAdvice
@RequestMapping("/sinks")
public class SinkController {

    final SinkRepository repo;

    @Autowired
    public SinkController(SinkRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String createIndex() {
        return "aloha sinks";
    }
}
