package hr.fer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Sink")
public class Sink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String uri;

    @NotNull
    private Integer userId;

    //region JPA things

    public Sink() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    //endregion

    @Override
    public String toString() {
        return "Sink{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", userId=" + userId +
                '}';
    }
}
