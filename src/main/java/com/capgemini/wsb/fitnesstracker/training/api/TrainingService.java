package com.capgemini.wsb.fitnesstracker.training.api;

/**
 * Interface (API) for writing operations on {@link Training} entities through the API.
 */
public interface TrainingService {

    /**
     * Creates a new training.
     *
     * @param training The training object containing the details of the training to be created.
     * @return The created training object.
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training.
     *
     * @param id The ID of the training to be updated.
     * @param training The training object containing the updated details of the training.
     * @return The updated training object.
     */
    Training updateTraining(Long id, Training training);
}
