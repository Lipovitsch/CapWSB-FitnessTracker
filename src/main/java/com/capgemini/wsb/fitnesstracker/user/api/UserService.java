package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {
    /**
     * Creates a new user.
     *
     * @param user The user entity to be created.
     * @return The created User entity.
     */
    User createUser(User user);

    /**
     * Deletes user by ID.
     *
     * @param userId The ID of the user to be deleted.
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user or creates a new one if the user with the given ID does not exist.
     *
     * @param id The ID of the user to be updated.
     * @param user The User entity that contains the updated information.
     * @return The updated User entity.
     */
    User updateUser(Long id, User user);
}
