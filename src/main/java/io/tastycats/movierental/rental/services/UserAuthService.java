//package io.tastycats.movierental.rental.services;
//
//import io.tastycats.movierental.rental.models.User;
//import io.tastycats.movierental.rental.repos.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserAuthService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
////        return userRepo.findUserByUserName(userName);
//        return null;
//    }
//
//    public User getUserByUserName(String userName) {
//        return userRepo.findUserByUserName(userName);
//    }
//}
