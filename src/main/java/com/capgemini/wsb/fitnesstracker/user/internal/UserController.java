package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody UserDto userDto) {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");

        // TODO: saveUser with Service and return User
        return userService.createUser(userMapper.toEntity(userDto));
//        return null;
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUserById(userDto.Id());
        System.out.println(userDto.Id());
//        System.out.println(userDto.email());
        Optional<User> user = userService.getUser(userDto.Id());
        System.out.println(user);
    }

}