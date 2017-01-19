package hr.fer.repository;

import hr.fer.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Integer> {

    List<Sensor> findByUserId(Integer userId);
    Sensor findBySensorId(String sensorId);

}