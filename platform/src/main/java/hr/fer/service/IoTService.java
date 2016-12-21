package hr.fer.service;


import hr.fer.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IoTService {

    private final SensorRepository sensorRepository;

    @Autowired
    public IoTService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Boolean pingSensor(){
        //TODO BORIS
        return null;
    }

    public Boolean changeSensorIpAdress(){
        //TODO BORIS
        return null;
    }
}
