package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TestMovieRepo {

    @Autowired
    private MovieRepo movieRepo;

    Pageable pageable;

    Movie testMovie;

    @BeforeEach
    public void setupObjects() {
        testMovie = new Movie();
        testMovie.setCopiesAvailable(13);
        testMovie.setGenres(List.of("horror"));
        testMovie.setMovieName("one movie");
        testMovie.setCast(List.of("actor", "actwo"));
        testMovie.setDirectorNames(List.of("director", "dirtwo"));
        pageable = PageRequest.of(0, 10);
        movieRepo.deleteAll();
    }

    @Test
    @DisplayName("repoStoreFetchTest")
    public void repoCanStore() {
        movieRepo.save(testMovie);
        Assertions.assertEquals(
                1,
                movieRepo.findAll().size()
        );
        Assertions.assertEquals(
                testMovie.getCopiesAvailable(),
                movieRepo.findAll().get(0).getCopiesAvailable()
        );
    }

    @Test
    public void repoCanSearch() {
        movieRepo.save(testMovie);

        Assertions.assertEquals(
                1,
                movieRepo.findAllByCastContaining("actor", pageable).getTotalElements()
        );
        Assertions.assertEquals(
                0,
                movieRepo.findAllByCastContaining("random", pageable).getTotalElements()
        );
    }
}
