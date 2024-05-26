package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for reading operations on {@link Training} entities through the API.
 */
public interface TrainingProvider {

    /**
     * Retrieves all trainings.
     *
     * @return {@link List} of all trainings
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves all trainings of a user.
     *
     * @param userId ID of the user
     * @return {@link List} of all trainings of the user
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Retrieves all trainings after a given date.
     *
     * @param date date after which the trainings should be retrieved
     * @return {@link List} of all trainings after the given date
     */
    List<Training> getTrainingsAfterDate(Date date);

    /**
     * Retrieves all trainings of a given activity type.
     *
     * @param activityType activity type of the trainings
     * @return {@link List} of all trainings of the given activity type
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);
}
