package io.tastycats.movierental.rental.services;


import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.repos.MovieRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class TestMovieService {

    @Mock
    private MovieRepo movieRepo;

    @InjectMocks
    private MovieService movieService;

    Movie testMovie;
    Movie theOtherMovie;

    List<Movie> testMovies;

    Map<String, String> queryMap;

    @BeforeEach
    public void setupObjects() {
        testMovie = new Movie();
        testMovie.setCopiesAvailable(13);
        testMovie.setGenres(List.of("horror"));
        testMovie.setMovieName("one movie");
        testMovie.setCast(List.of("actor", "actwo"));
        testMovie.setDirectorNames(List.of("director", "dirtwo"));

        theOtherMovie = new Movie();
        theOtherMovie.setCopiesAvailable(11);
        testMovies  = List.of(testMovie, theOtherMovie);

        queryMap = new HashMap<String, String>();

        Mockito.when(movieRepo.save(testMovie)).thenReturn(testMovie);
        Mockito.when(movieRepo.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<Movie>(List.of(testMovie)));
        Mockito.when(movieRepo.saveAll(Mockito.any(Iterable.class))).thenReturn(testMovies);
    }

    @Test
    public void serviceCanStore() {
        Assertions.assertEquals(
                testMovie.getCopiesAvailable(),
                movieService.addMovie(testMovie).getCopiesAvailable(),
                "check if service can store and retrieve data"
        );
    }

    @Test
    public void serviceCanFindAll() {
        Assertions.assertEquals(
                1,
                movieService.findAllMovies(queryMap).size(),
                "service can find all movies"
        );
    }

    @Test
    public void serviceCanAddMultiple() {
        Assertions.assertEquals(
                2,
                movieService.addMultiple(testMovies).size(),
                "service can add multiple movies"
        );


        Assertions.assertEquals(
                11,
                movieService.addMultiple(testMovies).get(1).getCopiesAvailable(),
                "and retrieve them reliably"
        );
    }
}
