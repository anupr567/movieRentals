package io.tastycats.movierental.rental.services;

import io.tastycats.movierental.rental.models.User;

import java.util.List;

public interface UserServiceInterface {
    User getUserById(String id);

    List<User> saveUsers(List<User> allUsers);
    User saveUser(User user);
    List<User> getUsers();
}
