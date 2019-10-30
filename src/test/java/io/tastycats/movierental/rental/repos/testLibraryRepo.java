package io.tastycats.movierental.rental.repos;


import io.tastycats.movierental.rental.models.Library;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class testLibraryRepo {

    @Autowired
    private LibraryRepo libraryRepo;

    @Autowired
            private MovieRepo movieRepo;
    @Autowired
            private UserRepo userRepo;

    Pageable pageable;

    Movie testMovie1, testMovie2, testMovie3, testMovie4;
    List<Movie> movieList = new ArrayList<>();
    Random random = new Random();
    int randomInt = random.nextInt(5);

    User user1, user2;
    List<User> userList = new ArrayList<>();

    Library presentLibrary1, presentLibrary2, presentLibrary3, historyLibrary1, historyLibrary2;
    List<Library> libraryList = new ArrayList<>();

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

        // setting up User and Library Database
        if(true){
            user1 = new User();
            user2 = new User();


            user1.setName("Sudhanshu");
            user1.setAddress("Indore");
            user1.setFine(50);
            user1.setEmail("sud.kul@gmail.com");
            List<String> presentBookingUser1 = new ArrayList<>();
//            presentBookingUser1.add(testMovie1.getId());
//            presentBookingUser1.add(testMovie2.getId());
//            user1.setPresentBookingIds(presentBookingUser1);
            List<String> historyBookingUser1 = new ArrayList<>();
//            historyBookingUser1.add(testMovie4.getId());
//            user1.setHistoryBookingIds(historyBookingUser1);
            user1.setWishList(List.of(testMovie3));
            user1.setPhoneNo("9876543210");

            user2.setName("Pratik");
            user2.setAddress("Chandrapur");
            user2.setFine(30);
            user2.setEmail("pra.nin@gmail.com");
            List<String> presentBookingUser2 = new ArrayList<>();
//            presentBookingUser2.add(testMovie2.getId());
//            user2.setPresentBookingIds(presentBookingUser2);
            List<String> historyBookingUser2 = new ArrayList<>();
//            historyBookingUser2.add(testMovie3.getId());
//            user2.setHistoryBookingIds(historyBookingUser2);
            user2.setWishList(List.of(testMovie1));
            user2.setPhoneNo("9012345678");

            presentLibrary1 = new Library();
            presentLibrary2 = new Library();
            presentLibrary3 = new Library();

            presentLibrary1.setMovieId(testMovie1.getId());
            presentLibrary1.setBookingDate(LocalDate.now());
            presentLibrary1.setReturnDate(LocalDate.now().plusDays(14));
            presentLibrary1.setUserId(user1.getId());
            presentLibrary1.setActive(true);

            presentLibrary2.setActive(true);
            presentLibrary2.setUserId(user2.getId());
            presentLibrary2.setBookingDate(LocalDate.now().minusDays(2));
            presentLibrary2.setBookingDate(LocalDate.now().plusDays(12));
            presentLibrary2.setMovieId(testMovie2.getId());

            presentLibrary3.setActive(true);
            presentLibrary3.setMovieId(testMovie2.getId());
            presentLibrary3.setBookingDate(LocalDate.now().minusDays(10));
            presentLibrary3.setReturnDate(LocalDate.now().plusDays(4));
            presentLibrary3.setUserId(user1.getId());

            presentBookingUser1.add(presentLibrary1.getId());
            presentBookingUser1.add(presentLibrary3.getId());
            user1.setPresentBookingIds(presentBookingUser1);

            presentBookingUser2.add(presentLibrary2.getId());
            user2.setPresentBookingIds(presentBookingUser2);

            historyLibrary1 = new Library();
            historyLibrary2 = new Library();

            historyLibrary1.setActive(false);
            historyLibrary1.setUserId(user1.getId());
            historyLibrary1.setMovieId(testMovie4.getId());
            historyLibrary1.setBookingDate(LocalDate.now().minusDays(50));
            historyLibrary1.setReturnDate(LocalDate.now().minusDays(36));
            historyBookingUser1.add(historyLibrary1.getId());
            user1.setHistoryBookingIds(historyBookingUser1);

            historyLibrary2.setActive(false);
            historyLibrary2.setUserId(user2.getId());
            historyLibrary2.setMovieId(testMovie3.getId());
            historyLibrary2.setReturnDate(LocalDate.now().minusDays(7));
            historyLibrary2.setBookingDate(LocalDate.now().minusDays(21));
            historyBookingUser2.add(historyLibrary1.getId());
            user2.setHistoryBookingIds(historyBookingUser2);

            libraryList.add(presentLibrary1);
            libraryList.add(presentLibrary2);
            libraryList.add(presentLibrary3);
            libraryList.add(historyLibrary1);
            libraryList.add(historyLibrary2);

//            libraryList = Arrays.asList(presentLibrary1,presentLibrary2,presentLibrary3, historyLibrary1, historyLibrary2);

            userList.add(user1);
            userList.add(user2);
//            userList = Arrays.asList(user1,user2);
            userRepo.saveAll(userList);
        }
    }

    @AfterEach
    public void deleteEverything(){
        userRepo.deleteAll();
        movieRepo.deleteAll();
        libraryRepo.deleteAll();
        userList.clear();
        movieList.clear();
        libraryList.clear();
    }

    @Test
    public void testSaveAll(){
        Assertions.assertEquals(
                libraryList.size(),
                libraryRepo.saveAll(libraryList).size()
        );

        Assertions.assertEquals(
                libraryList.get(randomInt),
                libraryRepo.saveAll(libraryList).get(randomInt)
        );

        Assertions.assertEquals(
                libraryList.get(randomInt).getUserId(),
                libraryRepo.findAll().get(randomInt).getUserId()
        );
    }

    @Test
    public void testFindAll(){
        libraryRepo.saveAll(libraryList);
        Assertions.assertEquals(
            libraryList.size(),
                libraryRepo.findAll().size()
        );
        Assertions.assertEquals(
                libraryList.get(randomInt).getReturnDate(),
                libraryRepo.findAll().get(randomInt).getReturnDate()
        );
        randomInt = random.nextInt(5);
        Assertions.assertEquals(
                libraryList.get(randomInt).isActive(),
                libraryRepo.findAll().get(randomInt).isActive()
        );
    }
    @Test
    public void testSaveOne(){

        Assertions.assertEquals(
                presentLibrary1,
                libraryRepo.save(presentLibrary1)
        );

        Assertions.assertEquals(
                presentLibrary1.getId(),
                libraryRepo.save(presentLibrary1).getId()
        );
    }

    @Test
    public void testFindById(){
        libraryRepo.saveAll(libraryList);
        Assertions.assertEquals(
                libraryList.get(randomInt).getId(),
                libraryRepo.findById(libraryList.get(randomInt).getId()).get().getId()
        );
        Assertions.assertEquals(
                libraryList.get(randomInt).isActive(),
                libraryRepo.findById(libraryList.get(randomInt).getId()).get().isActive()
        );

    }
}
