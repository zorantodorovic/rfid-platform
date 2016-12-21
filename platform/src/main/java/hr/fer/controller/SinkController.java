package hr.fer.controller;


import hr.fer.model.Sink;
import hr.fer.repository.SinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sinks")
public class SinkController {

    final SinkRepository sinkRepository;

    @Autowired
    public SinkController(SinkRepository sinkRepository) {
        this.sinkRepository = sinkRepository;
    }

    @GetMapping
    public Iterable<Sink> readSinks() {
        return sinkRepository.findAll();
    }

    @PostMapping
    public Sink createSink(@RequestBody Sink sink) {
        return sinkRepository.save(sink);
    }

    @PutMapping
    public Sink updateSink(@RequestBody Sink newSink) {
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
