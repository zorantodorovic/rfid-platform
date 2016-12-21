package hr.fer.repository;

import hr.fer.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {

    Iterable<Record> findBySensorId(Integer sensorId);
}
