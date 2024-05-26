package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Query searching trainings by user ID.
     *
     * @param userId ID of the user to search
     * @return {@link List} containing found trainings or empty list
     */
    default List<Training> findAllByUserId(Long userId) {
        return findAll().stream()
                        .filter(training -> {
                            assert training.getUser().getId() != null;
                            return training.getUser().getId().equals(userId);
                        })
                        .toList();
    }

    /**
     * Query searching trainings after given end time.
     *
     * @param date end time of the training to compare
     * @return {@link List} containing found trainings or empty list
     */
    default List<Training> findAllByEndTimeAfter(Date date){
        return findAll().stream()
                        .filter(training -> training.getEndTime().after(date))
                        .toList();
    }

    /**
     * Query searching trainings by activity type.
     *
     * @param activityType activity type of the training to search
     * @return {@link List} containing found trainings or empty list
     */
    default List<Training> findAllByActivityType(ActivityType activityType){
        return findAll().stream()
                        .filter(training -> training.getActivityType().equals(activityType))
                        .toList();
    }
}
