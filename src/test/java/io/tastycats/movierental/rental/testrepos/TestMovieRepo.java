package io.tastycats.movierental.rental.testrepos;

import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.repos.MovieRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TestMovieRepo {

    @Autowired
    private MovieRepo movieRepo;

    Movie testMovie;

    @BeforeEach
    public void setupObjects() {
        testMovie = new Movie();
        testMovie.setCopiesAvailable(13);
    }

    @Test
    @DisplayName("repoStoreFetchTest")
    public void repoCanStore() {
        movieRepo.save(testMovie);
        Assertions.assertEquals(1, movieRepo.findAll().size());
        Assertions.assertEquals(13, movieRepo.findAll().get(0).getCopiesAvailable());
    }
}
