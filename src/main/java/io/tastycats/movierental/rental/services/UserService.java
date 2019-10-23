package io.tastycats.movierental.rental.services;

import io.tastycats.movierental.rental.models.User;
import io.tastycats.movierental.rental.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserById(String id) {
        return userRepo.findById(id).get();
    }

//    public User saveUserById(String id) {

//    }

//    public User saveUser()

    public List<User> saveUsers(List<User> allUsers) {
        return userRepo.saveAll(allUsers);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
