package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getUserByEmail(final String email) {
        return userRepository.findByPartialEmailMatchIgnoreCase(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersOlderThan(final LocalDate date) {
        return userRepository.findAllByDateOfBirthBefore(date);
    }

    @Override
    public User updateUser(final Long userId, final User user) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        log.info("Updating User with ID {}", userId);

        User existingUser = userRepository.findById(userId).get();
        String firstName = Optional.ofNullable(user.getFirstName()).orElse(existingUser.getFirstName());
        String lastName = Optional.ofNullable(user.getLastName()).orElse(existingUser.getLastName());
        LocalDate birthdate = Optional.ofNullable(user.getBirthdate()).orElse(existingUser.getBirthdate());
        String email = Optional.ofNullable(user.getEmail()).orElse(existingUser.getEmail());

        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthdate(birthdate);
        user.setEmail(email);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        log.info("Deleting User with ID {}", userId);
        userRepository.delete(userRepository.findById(userId).get());
    }
}