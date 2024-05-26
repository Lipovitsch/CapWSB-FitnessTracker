package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for reading operations on {@link User} entities through the API.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with the given ID is not found, a UserNotFoundException is thrown.
     *
     * @param userId The ID of the user to be retrieved.
     * @return An Optional containing the user if found, otherwise an empty Optional.
     * @throws UserNotFoundException if the user with the given ID is not found.
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email address.
     *
     * @param email The email address of the user to be retrieved.
     * @return A list of users with the given email address.
     */
    List<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    List<User> findAllUsers();

    /**
     * Retrieves all users who are older than the specified date.
     *
     * @param date The date to compare against.
     * @return A list of users who are older than the specified date.
     */
    List<User> getUsersOlderThan(LocalDate date);
}
