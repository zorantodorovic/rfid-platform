package hr.fer.controller;


import hr.fer.model.Sensor;
import hr.fer.model.SensorSink;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.SensorSinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;
    private final SensorSinkRepository sensorSinkRepository;

    @Autowired
    public SensorController(SensorRepository sensorRepository, SensorSinkRepository sensorSinkRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorSinkRepository = sensorSinkRepository;
    }

    @GetMapping
    public Iterable<Sensor> readSensors() {
        return sensorRepository.findAll();
    }

    @PostMapping
    public Sensor createSensor(@RequestBody Map<String, Object> map) {
        Sensor sensor = (Sensor) map.getOrDefault("sensor", null);
        Iterable sinkIds = (Iterable) map.getOrDefault("sinkIds", null);
        if (sensor != null && sinkIds != null && sinkIds.iterator().hasNext()) {
            sensor = sensorRepository.save(sensor);
            for (Object sinkId : sinkIds) {
                sensorSinkRepository.save(new SensorSink(sensor.getId(), Integer.parseInt(String.valueOf(sinkId))));
            }
            return sensor;
        }
        return null;
    }

    @PutMapping
    public Sensor updateSensorIpAdressAndType(@RequestBody Sensor newSensor) {
        if (newSensor.getId() == null) {
            return null;
        }
        Sensor sensor = sensorRepository.findOne(newSensor.getId());
        if (newSensor.getIpAddress() != null) {
            sensor.setIpAddress(newSensor.getIpAddress());
        }
        if (newSensor.getSensorType() != null) {
            sensor.setSensorType(newSensor.getSensorType());
        }
        return sensorRepository.save(sensor);
    }

}