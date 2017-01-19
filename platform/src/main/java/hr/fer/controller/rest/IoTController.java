package hr.fer.controller.rest;

import hr.fer.model.Chip;
import hr.fer.model.Record;
import hr.fer.repository.ChipRepository;
import hr.fer.repository.SensorRepository;
import hr.fer.service.IoTService;
import hr.fer.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpHeaders.USER_AGENT;


/**
 * kontroler koji razgovara s vanjskom komponentom
 * npr. ako korisnik zeli pingati senzor da vidi jel ziv preko njega to radi
 */
@RestController
@RequestMapping("/rest/iot")
public class IoTController {

    private final IoTService ioTService;
    private final RecordService recordService;
    private final ChipRepository chipRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public IoTController(IoTService ioTService, RecordService recordService, ChipRepository chipRepository, SensorRepository sensorRepository) {
        this.ioTService = ioTService;
        this.recordService = recordService;
        this.chipRepository = chipRepository;
        this.sensorRepository = sensorRepository;
    }

    @PostMapping("/record")
    public Iterable<Record> submitNewRecord(String time, String uid, String id) throws ParseException {
        int chipId = chipRepository.findByUid(uid).getId();
        int sensorId = sensorRepository.findBySensorId(id).getId();
        Date parsedTime = new Date(Long.parseLong(time) * 1000L);

        Record rec = new Record(parsedTime, sensorId, chipId);
        return recordService.saveRecord(rec);
    }

    @PostMapping("/ping")
    public Boolean pingSensor() throws IOException {
        return ioTService.pingSensor();
    }

    @PostMapping("/changeIp")
    public Boolean changeIpAdress() throws IOException {
        return ioTService.changeSensorIpAdress();
    }

}
