package hr.fer.model;

import hr.fer.model.keys.SensorSinkKey;

import javax.persistence.*;

@Entity
@Table(name = "SensorSink")
@IdClass(SensorSinkKey.class)
public class SensorSink  {

    @Id
    private Integer sensorId;

    @Id
    private Integer sinkId;

    public SensorSink() {
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


    @Override
    public String toString() {
        return String.format("SensorSink{sensorId=%d, sinkId=%d}", sensorId, sinkId);
    }
}
