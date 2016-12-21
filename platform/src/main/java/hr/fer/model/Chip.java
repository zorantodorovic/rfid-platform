package hr.fer.model;

import javax.persistence.*;

@Entity
@Table(name = "Chip")
public class Chip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // region JPA things

    public Chip() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Chip{id=%d, name='%s'}", id, name);
    }

    //endregion
}
