package hr.fer.repository;

import hr.fer.model.SensorSink;
import hr.fer.model.User;
import hr.fer.model.keys.SensorSinkKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorSinkRepository extends CrudRepository<SensorSink, SensorSinkKey> {

    List<SensorSink> findBySensorId(Integer sensorId);
}
