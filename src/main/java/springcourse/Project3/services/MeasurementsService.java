package springcourse.Project3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcourse.Project3.models.Measurements;
import springcourse.Project3.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final SensorService sensorService;
    private final MeasurementsRepository measurementsRepository;

    public MeasurementsService(SensorService sensorService,
                               MeasurementsRepository measurementsRepository) {
        this.sensorService = sensorService;
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurements> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurements measurements) {
        enrichMeasurements(measurements);
        measurementsRepository.save(measurements);
    }


    public void enrichMeasurements(Measurements measurements) {
        measurements.setSensorName(sensorService.findByName(measurements.getSensorName().getName()).get());
        measurements.setCreatedAt(LocalDateTime.now()); // добавляем время создания
    }

    @Override
    public String toString() {
        return "MeasurementsService{" +
                "sensorService=" + sensorService +
                ", measurementsRepository=" + measurementsRepository +
                '}';
    }
}
