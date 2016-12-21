package hr.fer.model.keys;

import java.io.Serializable;

public class UserSensorKey implements Serializable{

    private Integer userId;

    private Integer sensorId;

    private Integer sinkId;

    public UserSensorKey() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
