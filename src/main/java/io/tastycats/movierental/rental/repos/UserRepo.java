package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    public User findUserByUserName(String userName);
}
