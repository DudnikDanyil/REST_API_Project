package springcourse.Project3.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurements")
public class Measurements {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor_name;

    @NotNull
    @Column(name = "value")
    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100, max = 100, message = "Name should be between -100 and 100 characters")
    private Integer value;

    @NotNull
    @NotEmpty(message = "Value should not be empty")
    @Column(name = "raining")
    private Boolean raining;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Autowired
    public Measurements(int id, Sensor sensor_name, int value, boolean raining, LocalDateTime createdAt) {
        this.id = id;
        this.sensor_name = sensor_name;
        this.value = value;
        this.raining = raining;
        this.createdAt = createdAt;
    }

    public Measurements() {

    }

    public Sensor getSensorName() {
        return sensor_name;
    }

    public void setSensorName(Sensor sensor_name) {
        this.sensor_name = sensor_name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "sensor='" + sensor_name + '\'' +
                ", value=" + value +
                ", raining=" + raining +
                '}';
    }
}
