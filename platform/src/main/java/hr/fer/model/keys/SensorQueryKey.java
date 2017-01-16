package hr.fer.model.keys;

import java.io.Serializable;

public class SensorQueryKey implements Serializable {
    private Integer sensorId;
    private Integer queryId;

    public SensorQueryKey() {
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getQueryId() {
        return queryId;
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }
}
