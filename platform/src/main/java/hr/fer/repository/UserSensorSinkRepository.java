package hr.fer.repository;

import hr.fer.model.UserSensorSink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSensorSinkRepository extends CrudRepository<UserSensorSink, Integer> {
}
