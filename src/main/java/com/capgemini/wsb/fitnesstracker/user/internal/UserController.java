package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDtoFirstName> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDtoFirstName)
                          .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.toDto(userService.getUser(id).orElseThrow());
    }

    @GetMapping("/email/{email}")
    public List<UserDtoEmail> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                          .stream()
                          .map(userMapper::toDtoEmail)
                          .toList();
    }

    @GetMapping("/age/{age}")
    public List<UserDto> getUsersOlderThanSpecifiedAge(@PathVariable int age) {
        // find all users older than the specified age
        return userService.getUsersOlderThanSpecifiedAge(age)
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    
    @PostMapping("/add")
    public User addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userMapper.toEntity(userDto));
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userMapper.toEntity(userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}