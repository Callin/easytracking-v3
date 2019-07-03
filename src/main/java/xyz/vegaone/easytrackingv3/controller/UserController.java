package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.service.UserService;

import java.util.List;

import static xyz.vegaone.easytrackingv3.controller.RestApiConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@NonNull @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@NonNull @RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("authenticate")
    public User authenticate(@NonNull @RequestBody User user) {
        return userService.authenticate(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
