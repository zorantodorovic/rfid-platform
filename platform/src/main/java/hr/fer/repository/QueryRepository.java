package hr.fer.repository;

import hr.fer.model.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends CrudRepository<Query, Integer> {

    List<Query> findAll();

    List<Query> findBySensorIdIn(Iterable<Integer> sensorId);

}
