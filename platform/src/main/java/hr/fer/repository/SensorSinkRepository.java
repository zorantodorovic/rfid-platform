package hr.fer.repository;

import hr.fer.model.SensorSink;
import hr.fer.model.User;
import hr.fer.model.keys.SensorSinkKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorSinkRepository extends CrudRepository<SensorSink, SensorSinkKey> {

    Iterable<SensorSink> findBySensorId(Integer sensorId);
}
