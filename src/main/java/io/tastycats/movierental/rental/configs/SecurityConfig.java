//package io.tastycats.movierental.rental.configs;
//
//import io.tastycats.movierental.rental.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
////    @Override
////    public void configure(HttpSecurity http) throws Exception{
////        http.authorizeRequests().antMatchers("/").
////                access("hasRole('ADMIN')").and().logout();
////    }
//}
