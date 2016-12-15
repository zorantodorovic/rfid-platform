package hr.fer.controller;


import hr.fer.model.Query;
import hr.fer.repository.QueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * kontroleri koji razgovara s vanjskom komponentom
 * npr ako treba spremiti podatak u bazu
 * npr ako treba vidjeti jel korisnikova baza ziva
 */
@RestController
@RequestMapping("/queries")
public class QueryController {

    QueryRepository repo;

    @GetMapping
    public Iterable<Query> readQueries() {
        return repo.findAll();
    }
}
