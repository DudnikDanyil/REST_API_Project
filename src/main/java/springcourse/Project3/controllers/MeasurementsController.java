package springcourse.Project3.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcourse.Project3.dto.MeasurementsDTO;
import springcourse.Project3.models.Measurements;
import springcourse.Project3.services.MeasurementsService;
import springcourse.Project3.util.MeasurementsErrorResponse;
import springcourse.Project3.util.MeasurementsException;
import springcourse.Project3.util.MeasurementsValidator;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static springcourse.Project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;

    private final MeasurementsValidator measurementsValidator;

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService,
                                  MeasurementsValidator measurementsValidator,
                                  ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.measurementsValidator = measurementsValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                             BindingResult bindingResult) { // аннотация @RequestBody автоматически конвертирует полученный JSON в объект класса Person
        Measurements measurementsToAdd = convertToMeasurements(measurementsDTO);
        measurementsValidator.validate(measurementsToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        measurementsService.save(measurementsToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping()
    public List<MeasurementsDTO> getMeasurements() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public List<MeasurementsDTO> getMeasurementsRainyDaysCount() {
        return measurementsService.findAll().stream().filter(element -> element.isRaining() == true)
                .map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList());
    }


    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResponse> handleException(MeasurementsException e) {
        MeasurementsErrorResponse response = new MeasurementsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
