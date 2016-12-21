package hr.fer.repository;

import hr.fer.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Integer> {

    Iterable<Sensor> findByUserId(Integer userId);

}