package io.tastycats.movierental.rental.controllers;


import io.tastycats.movierental.rental.models.User;
import io.tastycats.movierental.rental.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        try{
            User user = userService.getUserById(id);
        } catch(Exception e){
            logger.error("No Such User Id Exist for getting the user by Id");
        }
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

    @GetMapping("/{id}/wishList")
    public User getUserWishList(@PathVariable("id") String id) {
        try{
            User user = userService.getWishList(id);
        } catch (Exception e){
            logger.error("No such User Id exist for getting the Wishlist by UserId");
        }
        return userService.getWishList(id);
    }

    @PostMapping("/{userId}/{movieId}")
    public User addMovieToWishList(@PathVariable("userId") String userId, @PathVariable("movieId") String movieId){
        try{
            User user = userService.addMovieToWishList(userId,movieId);
        } catch (Exception e){
            logger.error("No such User Id or Movie Id exist for getting the Wishlist by UserId");
        }
        return userService.addMovieToWishList(userId,movieId);
    }
}
