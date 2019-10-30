package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Movie;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TestMovieRepoEmbeddedMongo {

    @Autowired
    private MovieRepo movieRepo;

    Pageable pageable;

    Movie testMovie1, testMovie2, testMovie3, testMovie4;
    List<Movie> movieList = new ArrayList<>();
    Random random = new Random();
    int randomInt = random.nextInt(4);

    @BeforeEach
    public void setupObjects() {
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

    @AfterEach
    public void cleanObjects() {
        movieRepo.deleteAll();
        movieList.clear();
    }

    @Test
    @DisplayName("repoStoreFetchTest")
    public void movieRepoCanStore() {
//        Assertions.assertEquals(
//                1,
//                movieRepo.findAll().size()
//        );
//        Assertions.assertEquals(
//                testMovie.getCopiesAvailable(),
//                movieRepo.findAll().get(0).getCopiesAvailable()
//        );
        Assertions.assertEquals(
                4,
                movieRepo.findAll().size()
        );
        Assertions.assertEquals(
                movieList.get(randomInt).getMovieName(),
                movieRepo.findAll().get(randomInt).getMovieName()
        );
    }

    @Test
    public void movieRepoCanSearch() {
//        Assertions.assertEquals(
//                4,
//                movieRepo.findAllByCastContaining("actor", pageable).getTotalElements()
//        );
//        Assertions.assertEquals(
//                0,
//                movieRepo.findAllByCastContaining("random", pageable).getTotalElements()
//        );
//        List<Movie> ml = movieRepo.findAllByCastContaining("Anup", pageable).getContent();
        Assertions.assertEquals(
                1,
                movieRepo.findAllByCastContaining("Anup", pageable).getTotalElements()
        );
        Assertions.assertEquals(
                1,
                movieRepo.findAllByMovieNameContaining("P Art", pageable).getTotalElements()
        );
        Assertions.assertEquals(
                0,
                movieRepo.findAllByMovieNameContaining("P art", pageable).getTotalElements()
        );
    }

}
