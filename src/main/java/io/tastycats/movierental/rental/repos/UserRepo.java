package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
//    public User findUserByUserName(String userName);
    //    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'user.wishList' : 1 }")  // using Query get only wishList
//    Optional<User> findById(String userId);
}
