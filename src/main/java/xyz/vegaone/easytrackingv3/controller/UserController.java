package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.service.UserService;

@RestController
@RequestMapping("/user")
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
    @PostMapping("authenticate")
    public User authenticate(@RequestBody User user) {
        return userService.authenticate(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
