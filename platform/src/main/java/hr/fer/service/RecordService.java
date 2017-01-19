package hr.fer.service;

import hr.fer.model.Record;
import hr.fer.model.Sensor;
import hr.fer.model.SensorSink;
import hr.fer.model.Sink;
import hr.fer.repository.RecordRepository;
import hr.fer.repository.SensorRepository;
import hr.fer.repository.SensorSinkRepository;
import hr.fer.repository.SinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final SinkRepository sinkRepository;
    private final SensorRepository sensorRepository;
    private final SensorSinkRepository sensorSinkRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository,
                         SinkRepository sinkRepository,
                         SensorRepository sensorRepository,
                         SensorSinkRepository sensorSinkRepository) {
        this.recordRepository = recordRepository;
        this.sinkRepository = sinkRepository;
        this.sensorRepository = sensorRepository;
        this.sensorSinkRepository = sensorSinkRepository;
    }

    public Set<Record> readRecords(List<Integer> sensorIds) {
        Set<Record> result = new HashSet<>();
        for (Integer sensorId : sensorIds) {
            if (sensorId != null) {
                Iterable<SensorSink> sensorSinks = sensorSinkRepository.findBySensorId(sensorId);
                for (SensorSink sensorSink : sensorSinks) {
                    Sink sink = sinkRepository.findOne(sensorSink.getSinkId());
                    Iterable<Record> recordsFromSink = readRecordsFromSink(sensorId, sink);
                    for (Record record : recordsFromSink) {
                        result.add(record);
                    }
                }
            }
        }
        return result;
    }

    public List<Record> readRecordsFromSink(Integer sensorId, Sink sink) {
        //TODO treba napraviti komponentu koja ce iz razlicitih
        //TODO sink-ova (preko uri iz sink-a (jdbc)) dohvatiti recordse s odredjenim sensorId-jem.
        return recordRepository.findBySensorId(sensorId);
    }

    public List<Record> saveRecord(Record record) {
        Sensor sensor = sensorRepository.findOne(record.getSensorId());
        List<SensorSink> sensorSinks = sensorSinkRepository.findBySensorId(sensor.getId());
        List<Record> result = new ArrayList<>();
        for (SensorSink sensorSink : sensorSinks) {
            Sink sink = sinkRepository.findOne(sensorSink.getSinkId());
            //TODO s jdbc konektorom spremi na taj uri taj rekord
            //TODO  sink.getUri();
            result.add(recordRepository.save(record));
        }
        return result;
    }

    public void deleteRecords(Integer id) {
        recordRepository.delete(id);
    }

    public Iterable<Record> readRecordsFromUser(Integer userId) {
        List<Integer> sensorIds = sinkRepository.findByUserId(userId)
                .stream().map(Sink::getId).collect(Collectors.toList());

        return recordRepository.findBySensorIdIn(sensorIds);
    }
}
