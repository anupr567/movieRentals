package io.tastycats.movierental.rental;

import io.tastycats.movierental.rental.models.User;
import io.tastycats.movierental.rental.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class RentalApplication implements CommandLineRunner {

public class RentalApplication{
//	@Autowired
//	UserRepo userRepo;
	public static void main(String[] args) {
		SpringApplication.run(RentalApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		User admin = new User();
//		admin.setUserName("admin");
//		admin.setPassword("$2a$10$lNwUEiaakvgkjU13d4g/i.6IkuKdfGBbLQx.S8GZiTN2cX9oeCrQC");
//		admin.setRole("ADMIN");
//		userRepo.save(admin);

//		User user = new User();
//		user.setUserName("user");
//		user.setRole("USER");
//		user.setPassword("$2a$10$LDjTY1GyM93qPoQs2DAg7OuEN2otsPmbCxNdFGzgBqwyiQ7oQVQy2");
//		userRepo.save(user);
//	}
}
