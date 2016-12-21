package hr.fer.controller.web.viewbags;

import java.util.List;

public class UserSensorsBag {
    List<Long> sensorIds;
    Long userId;

    public UserSensorsBag() {
    }

    public UserSensorsBag(List<Long> sensorIds, Long userId) {
        this.sensorIds = sensorIds;
        this.userId = userId;
    }

    public List<Long> getSensorIds() {
        return sensorIds;
    }

    public void setSensorIds(List<Long> sensorIds) {
        this.sensorIds = sensorIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
