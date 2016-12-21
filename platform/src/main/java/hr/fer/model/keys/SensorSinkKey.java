package hr.fer.model.keys;

import java.io.Serializable;

public class SensorSinkKey implements Serializable {
    private Integer sensorId;
    private Integer sinkId;

    public SensorSinkKey() {
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getSinkId() {
        return sinkId;
    }

    public void setSinkId(Integer sinkId) {
        this.sinkId = sinkId;
    }
}
