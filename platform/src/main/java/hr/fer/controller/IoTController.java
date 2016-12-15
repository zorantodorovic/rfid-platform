package hr.fer.controller;

import hr.fer.model.Record;
import hr.fer.service.IoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * kontroler koji razgovara s vanjskom komponentom
 * npr. ako korisnik zeli pingati senzor da vidi jel ziv preko njega to radi
 */
@RestController
@RequestMapping("/iot")
public class IoTController {

    private final IoTService ioTService;

    @Autowired
    public IoTController(IoTService ioTService) {
        this.ioTService = ioTService;
    }

    @PostMapping("/record")
    public Record submitNewRecord() {
        return null;
    }

    @PostMapping("/ping")
    public String pingSensor() {
        return null;
    }

}
