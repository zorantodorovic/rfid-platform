package hr.fer.service;

import hr.fer.model.Query;
import hr.fer.model.Record;
import hr.fer.repository.QueryRepository;
import hr.fer.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    private final QueryRepository queryRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository,
                        RecordRepository recordRepository) {
        this.queryRepository = queryRepository;
        this.recordRepository = recordRepository;
    }

    public List<Query> readQueries(List<Integer> sensorIds) {
        return queryRepository.findBySensorIdIn(sensorIds);
    }

    public List<Record> submitQuery(Query query) {
        if (query.getSensorId() != null && query.getEndDateTime() != null && query.getStartDateTime() != null) {
            queryRepository.save(query);
            return recordRepository.findBySensorIdAndDateTime(
                    query.getSensorId(),
                    query.getStartDateTime(),
                    query.getEndDateTime()
            );
        }
        return new ArrayList<>();
    }

    public void deleteQuery(Integer queryId) {
        queryRepository.delete(queryId);
    }
}
