package hr.fer.controller.rest;

import hr.fer.model.Record;
import hr.fer.service.IoTService;
import hr.fer.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * kontroler koji razgovara s vanjskom komponentom
 * npr. ako korisnik zeli pingati senzor da vidi jel ziv preko njega to radi
 */
@RestController
@RequestMapping("/rest/iot")
public class IoTController {

    private final IoTService ioTService;
    private final RecordService recordService;

    @Autowired
    public IoTController(IoTService ioTService, RecordService recordService) {
        this.ioTService = ioTService;
        this.recordService = recordService;
    }

    @PostMapping("/record")
    public Iterable<Record> submitNewRecord(@RequestBody Record record) {
        return recordService.saveRecord(record);
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
