package hr.fer.repository;

import hr.fer.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository  extends CrudRepository<Query, Integer> {
}
