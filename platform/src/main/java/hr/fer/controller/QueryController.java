package hr.fer.controller;


import hr.fer.model.Query;
import hr.fer.model.Record;
import hr.fer.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * kontroleri koji razgovara s vanjskom komponentom
 * npr ako treba spremiti podatak u bazu
 * npr ako treba vidjeti jel korisnikova baza ziva
 */
@RestController
@RequestMapping("/queries")
public class QueryController {

    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public Iterable<Query> readQueries() {
        return queryService.readQueries();
    }

    @PostMapping
    public Iterable<Record> getFilteredRecords(@RequestBody Query query) {
        return queryService.submitQuery(query);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuery(@PathVariable Integer id){
       queryService.deleteQuery(id);
    }
}
