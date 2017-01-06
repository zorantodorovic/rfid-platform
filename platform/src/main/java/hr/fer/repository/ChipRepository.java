package hr.fer.repository;

import hr.fer.model.Chip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChipRepository extends CrudRepository<Chip, Integer> {}
