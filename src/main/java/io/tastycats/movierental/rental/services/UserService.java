package io.tastycats.movierental.rental.services;

import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.models.User;
import io.tastycats.movierental.rental.repos.MovieRepo;
import io.tastycats.movierental.rental.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private MovieService movieService;

    @Override
    public User getUserById(String id) {
            return userRepo.findById(id).get();
    }

//    public User saveUserById(String id) {

//    }

//    public User saveUser()

    @Override
    public List<User> saveUsers(List<User> allUsers) {
        return userRepo.saveAll(allUsers);
    }

    @Override
    public User saveUser(User user){ return userRepo.save(user); }
    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addMovieToWishList(String userId,String movieId) {
        User user = getUserById(userId);
        Movie movie = movieService.getMovieById(movieId);
        user.addMovieToWishList(movie);
        return userRepo.save(user);
    }

    @Override
    public List<Movie> getWishList(String id) {
        return userRepo.findById(id).get().getWishList();
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User currentUser = userRepo.findUserByUserName(userName);
//        UserDetails user = new org.springframework.security.core.userdetails.User(userName, currentUser.getPassword()
//        , true, true, true, true, AuthorityUtils.createAuthorityList(currentUser.getRole()));
//        return user;
//    }
}
