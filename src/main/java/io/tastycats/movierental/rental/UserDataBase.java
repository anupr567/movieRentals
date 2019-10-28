//package io.tastycats.movierental.rental;
//
//import io.tastycats.movierental.rental.models.User;
//import io.tastycats.movierental.rental.repos.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//
//public class UserDataBase {
//    @Autowired
//    private UserRepo userRepo;
//
//    @Bean
//    CommandLineRunner runner(){
//        return args -> {
//            User user = new User();
//            user.setUserName("user");
//            user.setPassword("");
//        }
//    }
//}
