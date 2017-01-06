package hr.fer.controller.rest;


import hr.fer.security.Current;
import hr.fer.model.Sink;
import hr.fer.repository.SinkRepository;
import hr.fer.repository.UserRepository;
import hr.fer.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/sinks")
public class SinkController {

    private final SinkRepository sinkRepository;

    @Autowired
    public SinkController(SinkRepository sinkRepository) {
        this.sinkRepository = sinkRepository;
    }

    @GetMapping
    public List<Sink> readSinks(@Current CurrentUser user) {
        return sinkRepository.findByUserId(user.getId());
    }

    @PostMapping
    public Sink createSink(@RequestBody Sink sink) {
        return sinkRepository.save(sink);
    }

    @PutMapping("{id}")
    public Sink updateSink(@PathVariable Integer id, @RequestBody Sink newSink) {
        if (newSink.getId() == null) {
            return null;
        }
        Sink sink = sinkRepository.findOne(newSink.getId());
        if (newSink.getUri() != null) {
            sink.setUri(newSink.getUri());
        }
        return sinkRepository.save(sink);
    }

}