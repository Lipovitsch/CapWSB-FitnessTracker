package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/finished/{date}")
    public List<TrainingDto> getTrainingsAfterDate(@PathVariable String date) throws ParseException {
        Date date_parsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return trainingService.getTrainingsAfterDate(date_parsed)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody TrainingDtoBasic trainingDtoBasic) {
        Training createdTraining = trainingService.createTraining(trainingMapper.toEntityBasic(trainingDtoBasic));
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody TrainingDtoBasic trainingDtoBasic) {
        return trainingService.updateTraining(id, trainingMapper.toEntityBasic(trainingDtoBasic));
    }
}
