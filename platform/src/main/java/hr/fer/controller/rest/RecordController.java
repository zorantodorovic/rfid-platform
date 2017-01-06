package hr.fer.controller.rest;

import hr.fer.model.Record;
import hr.fer.security.Current;
import hr.fer.security.CurrentUser;
import hr.fer.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/records")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public Iterable<Record> readRecords(@Current CurrentUser user) {
        return recordService.readRecordsFromUser(user.getId());
    }

    @PostMapping
    public Iterable<Record> readRecords(@Current CurrentUser user, @RequestBody Map<String, Object> sensorsMap) {
        Iterable sensorIds = (Iterable) sensorsMap.getOrDefault("sensorIds", null);
        if (sensorIds != null) {
            List<Integer> sensorIdIntegers = new ArrayList<>();
            for (Object sensorId : sensorIds) {
                sensorIdIntegers.add(Integer.parseInt(String.valueOf(sensorId)));
            }
            return recordService.readRecords(sensorIdIntegers);
        }
        return null;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecord(@PathVariable Integer id) {
        recordService.deleteRecords(id);
    }

}