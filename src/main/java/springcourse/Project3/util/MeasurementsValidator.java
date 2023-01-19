package springcourse.Project3.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import springcourse.Project3.models.Measurements;
import springcourse.Project3.services.SensorService;

@Component
public class MeasurementsValidator implements Validator {

    private final SensorService sensorService;

    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurements.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurements measurements = (Measurements) o;

        if (measurements.getSensorName() == null) {
            return;
        }
        if (sensorService.findByName(measurements.getSensorName().getName()).isEmpty())
            errors.rejectValue("sensor_name", "No sensor is registered with this name!");

    }

}