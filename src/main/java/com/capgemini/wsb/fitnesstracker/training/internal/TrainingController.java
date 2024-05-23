package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    // define api get mapping for searching trainings finished after specified date
    @GetMapping("/after/{date}")
    public List<TrainingDto> getTrainingsAfterDate(@PathVariable String date) throws ParseException {
        // convert string to date
        Date date_parsed = DateFormat.getDateInstance().parse(date);
        return trainingService.getTrainingsAfterDate(date_parsed)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @PostMapping("/add")
    public Training addTraining(@RequestBody TrainingDto trainingDto) {
        return trainingService.createTraining(trainingMapper.toEntity(trainingDto));
    }
}
