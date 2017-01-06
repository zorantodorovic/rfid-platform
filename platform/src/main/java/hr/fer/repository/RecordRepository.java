package hr.fer.repository;

import hr.fer.model.Record;
import hr.fer.model.Sink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {

    List<Record> findBySensorId(Integer sensorId);

    @Query("select r from Record r where r.sensorId = ?1 and r.dateTime >= ?2 and r.dateTime <= ?3")
    List<Record> findBySensorIdAndDateTime(Integer sensorId, Date startDateTime, Date endDateTime);

    List<Record> findBySensorIdIn(List<Sink> sensorIds);
}
