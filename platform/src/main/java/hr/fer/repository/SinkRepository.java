package hr.fer.repository;

import hr.fer.model.Sink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinkRepository extends CrudRepository<Sink, Integer> {
}