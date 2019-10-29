package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.ActiveProfiles;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TestUserRepo {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    Pageable pageable;

    Movie testMovie1, testMovie2, testMovie3, testMovie4;
    List<Movie> movieList = new ArrayList<>();
    Random random = new Random();
    int randomInt = random.nextInt(2);

    User user1, user2;
    List<User> userList = new ArrayList<>();


    @BeforeEach
    public void setupObjects() {
        // setting up movie database
        if (true) {
            pageable = PageRequest.of(0, 10);
            testMovie1 = new Movie();
            testMovie2 = new Movie();
            testMovie3 = new Movie();
            testMovie4 = new Movie();

            testMovie1.setCopiesAvailable(13);
            testMovie1.setGenres(List.of("horror"));
            testMovie1.setMovieName("An Uraag's Headphones");
            testMovie1.setCast(List.of("actor", "Anuraag"));
            testMovie1.setDirectorNames(List.of("director", "Rawat"));
            testMovie1.setDescription("Dark Green Colored Headphones take you to the mysterious Island of Zombies when you listen to Zombies by The Cranberries" +
                    " and You can't come back unless you finish the whole song. Whatever happens in the Island, Doesn't only stay in the Island.");
            movieList.add(testMovie1);

            testMovie2.setCopiesAvailable(54);
            testMovie2.setGenres(List.of("fantasy"));
            testMovie2.setMovieName("An Anyo of a Keyboard");
            testMovie2.setCast(List.of("actor", "Ananyo"));
            testMovie2.setDirectorNames(List.of("director", "Sen"));
            testMovie2.setDescription("Whatever you type on Obins Mechanical keyboard will come alive" +
                    ". A programmer discovers it too late and his life is changed to unknowable limits.");
            movieList.add(testMovie2);

            testMovie3.setCopiesAvailable(32);
            testMovie3.setGenres(List.of("Social Drama"));
            testMovie3.setMovieName("P Art Hair");
            testMovie3.setCast(List.of("actor", "Parth"));
            testMovie3.setDirectorNames(List.of("director", "Maheshwari"));
            testMovie3.setDescription("Frustrated from hair fall, a boy raises the issue " +
                    "of Water Quality and lack of Municipal actions to mitigate scarcity and waste" +
                    " through Street paintings.");
            movieList.add(testMovie3);

            testMovie4.setCopiesAvailable(23);
            testMovie4.setGenres(List.of("Sci-Fi"));
            testMovie4.setMovieName("An Up in Life");
            testMovie4.setCast(List.of("actor", "Anup"));
            testMovie4.setDirectorNames(List.of("director", "Singh"));
            testMovie4.setDescription("A bored physician's discovery of Carbon's brother" +
                    " Tardon opens up a portal connecting two different parallel universe.");
            movieList.add(testMovie4);

            movieRepo.saveAll(movieList);

        }

        // setting up User Database
        if(true){
            user1 = new User();
            user2 = new User();


            user1.setName("Sudhanshu");
            user1.setAddress("Indore");
            user1.setFine(50);
            user1.setEmail("sud.kul@gmail.com");
            List<String> presentBookingUser1 = new ArrayList<>();
            presentBookingUser1.add(testMovie1.getId());
            presentBookingUser1.add(testMovie2.getId());
            user1.setPresentBookingIds(presentBookingUser1);
            List<String> historyBookingUser1 = new ArrayList<>();
            historyBookingUser1.add(testMovie4.getId());
            user1.setHistoryBookingIds(historyBookingUser1);
            user1.setWishList(List.of(testMovie3));
            user1.setPhoneNo("9876543210");

            user2.setName("Pratik");
            user2.setAddress("Chandrapur");
            user2.setFine(30);
            user2.setEmail("pra.nin@gmail.com");
            List<String> presentBookingUser2 = new ArrayList<>();
            presentBookingUser2.add(testMovie2.getId());
            user2.setPresentBookingIds(presentBookingUser2);
            List<String> historyBookingUser2 = new ArrayList<>();
            historyBookingUser2.add(testMovie3.getId());
            user2.setHistoryBookingIds(historyBookingUser2);
            user2.setWishList(List.of(testMovie1));
            user2.setPhoneNo("9012345678");

            userList.add(user1);
            userList.add(user2);
            userRepo.saveAll(userList);
        }
    }

    @AfterEach
    public void cleanObjects() {
        movieRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    public void userRepoCanStore(){
        Assertions.assertEquals(
                userList.size(),
                userRepo.findAll().size()
        );
        Assertions.assertEquals(
                userList.get(randomInt).getAddress(),
                userRepo.findAll().get(randomInt).getAddress()
        );
        randomInt = random.nextInt(2);
        Assertions.assertEquals(
                userList.get(randomInt).getPresentBookingIds(),
                userRepo.findAll().get(randomInt).getPresentBookingIds()
        );

        Assertions.assertEquals(
                userList.get(randomInt),
                userRepo.save(userList.get(randomInt))
        );


        Assertions.assertEquals(
                userList.get(randomInt).getWishList(),
                userRepo.save(userList.get(randomInt)).getWishList()
        );
    }

}
