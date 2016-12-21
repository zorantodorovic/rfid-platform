package hr.fer.service;

import hr.fer.model.Record;
import hr.fer.model.Sensor;
import hr.fer.model.SensorSink;
import hr.fer.model.Sink;
import hr.fer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final RecordRepository recordRepository;

    private final SinkRepository sinkRepository;
    private final SensorRepository sensorRepository;
    private final SensorSinkRepository sensorSinkRepository;

    @Autowired
    public StorageService(RecordRepository recordRepository, SinkRepository sinkRepository, SensorRepository sensorRepository, SensorSinkRepository sensorSinkRepository) {
        this.recordRepository = recordRepository;
        this.sinkRepository = sinkRepository;
        this.sensorRepository = sensorRepository;
        this.sensorSinkRepository = sensorSinkRepository;
    }

    public Iterable<Record> getRecords(Integer userId, List<Integer> sensorIds) {
        Set<Record> result = new HashSet<>();
        for (Integer sensorId : sensorIds) {
            if (sensorId != null) {
                Iterable<SensorSink> sensorSinks = sensorSinkRepository.findBySensorId(sensorId);
                for (SensorSink sensorSink : sensorSinks) {
                    Sink sink = sinkRepository.findOne(sensorSink.getSinkId());
                    Iterable<Record> recordsFromSink = getRecordsFromSink(sensorId, sink);
                    for (Record record : recordsFromSink) {
                        result.add(record);
                    }
                }
            }
        }
        return result;
    }

    public Iterable<Record> getRecordsFromSink(Integer sensorId, Sink sink) {
        //TODO treba napraviti komponentu koja ce iz razlicitih
        //TODO sink-ova (preko uri iz sink-a (jdbc)) dohvatiti recordse s odredjenim sensorId-jem.
        return recordRepository.findBySensorId(sensorId);
    }

    public Iterable<Record> saveRecord(Record record) {
        Sensor sensor = sensorRepository.findOne(record.getSensorId());
        Iterable<SensorSink> sensorSinks = sensorSinkRepository.findBySensorId(sensor.getId());
        Collection<Record> result = new ArrayList<>();
        for (SensorSink sensorSink : sensorSinks) {
            Sink sink = sinkRepository.findOne(sensorSink.getSinkId());
            //TODO s jdbc konektorom spremi na taj uri taj rekord
            //TODO  sink.getUri();
            result.add(recordRepository.save(record));
        }
        return result;
    }


}
