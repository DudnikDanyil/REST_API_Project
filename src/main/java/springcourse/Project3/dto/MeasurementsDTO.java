package springcourse.Project3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MeasurementsDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @JsonProperty("sensor_name")
    private SensorDTO sensor_name;

    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100, max = 100, message = "Name should be between -100 and 100 characters")
    private Integer value;

    @NotEmpty(message = "Value should not be empty")
    private Boolean raining;

    public SensorDTO getSensorName() {
        return sensor_name;
    }

    public void setSensorName(SensorDTO sensor_name) {
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
}
