package hr.fer.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hr.fer.model.Record;
import hr.fer.service.IoTService;
import hr.fer.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * kontroler koji razgovara s vanjskom komponentom
 * npr. ako korisnik zeli pingati senzor da vidi jel ziv preko njega to radi
 */
@RestController
@RequestMapping("/iot")
public class IoTController {

    private final IoTService ioTService;
    private final StorageService storageService;

    @Autowired
    public IoTController(IoTService ioTService, StorageService storageService) {
        this.ioTService = ioTService;
        this.storageService = storageService;
    }

    @PostMapping("/record")
    public Iterable<Record> submitNewRecord(@RequestBody Record record) {
        return storageService.saveRecord(record);
    }

    @PostMapping("/ping")
    public Boolean pingSensor() {
        //TODO BORIS
        return ioTService.pingSensor();
    }

    @PostMapping("/changeIp")
    public Boolean changeIpAdress(){
        // TODO BORIS
        return ioTService.changeSensorIpAdress();
    }

}
