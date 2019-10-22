package io.tastycats.movierental.rental.controllers;


import io.tastycats.movierental.rental.models.User;
import io.tastycats.movierental.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public List<User> saveUsers(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }
}
