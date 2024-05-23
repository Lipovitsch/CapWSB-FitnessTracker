package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public List<User> getUsersOlderThanSpecifiedAge(final int age) {
        return userRepository.findAll().stream()
                .filter(user -> user.getAge() > age)
                .toList();
    }

    @Override
    public User updateUser(final Long userId, final User user) {
        log.info("Updating User with ID {}", userId);
        if (userRepository.findById(userId).isEmpty()) {
            log.info("No existing User with id {} has been found, creating new User", userId);
            return createUser(user);
        }
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.delete(userRepository.findById(userId).get());
    }

}