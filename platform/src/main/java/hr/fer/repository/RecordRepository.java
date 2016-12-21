package hr.fer.repository;

import hr.fer.model.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {

    Iterable<Record> findBySensorId(Integer sensorId);

    @Query("select r from Record r where r.sensorId = ?1 and r.dateTime >= ?2 and r.dateTime <= ?3")
    Iterable<Record> findBySensorIdAndDateTime(Integer sensorId, Date startDateTime, Date endDateTime);

}
