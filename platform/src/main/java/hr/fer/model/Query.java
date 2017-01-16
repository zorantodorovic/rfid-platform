package hr.fer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer sensorId;

    private Date startDateTime;
    private Date endDateTime;
    
    // Optional
    private Integer count;

    //region JPA things

    public Query() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date parsed = format.parse(startDateTime);
        this.startDateTime = parsed;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date parsed = format.parse(endDateTime);
        this.endDateTime = parsed;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    //endregion


    @Override
    public String toString() {
        return String.format("Query{id=%d, sensorId=%d, startDateTime=%s, endDateTime=%s, count=%d}", id, sensorId, startDateTime, endDateTime, count);
    }
}