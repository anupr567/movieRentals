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
import org.springframework.util.Assert;

import java.util.*;

@ExtendWith(SpringExtension.class)
public class TestMovieServiceMockito {

    @Mock
    private MovieRepo movieRepo;

    @InjectMocks
    private MovieService movieService;

    Pageable pageable;
    Movie testMovie1, testMovie2, testMovie3, testMovie4;
    List<Movie> movieList = new ArrayList<>();
    Random random = new Random();
    int randomInt = random.nextInt(4);


    Map<String, String> queryMap;

    @BeforeEach
    public void setupObjects() throws Exception {

        //setting up Movie List
        if(true){
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
        }

        queryMap = new HashMap<String, String>();

        Mockito.when(movieRepo.save(testMovie1)).thenReturn(testMovie1);
        Mockito.when(movieRepo.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<Movie>(movieList));
//        Mockito.when(movieRepo.saveAll(movieList)).thenReturn(movieList);
        Mockito.when(movieRepo.saveAll(movieList)).thenReturn(movieList);
        Mockito.when(movieRepo.findAll()).thenReturn(movieList);
//        Mockito.when(mov)
    }

    @Test
    public void serviceCanStore() {
        Assertions.assertEquals(
                testMovie1.getCopiesAvailable(),
                movieService.addMovie(testMovie1).getCopiesAvailable(),
                "check if service can store and retrieve data"
        );

    }

    @Test
    public void serviceCanFindAll() {
        Assertions.assertEquals(
                4,
                movieService.findAllMovies(queryMap).size(),
                "service can find all movies"
        );
    }

    @Test
    public void serviceCanAddMultiple() {
        Assertions.assertEquals(
                movieList.size(),
                movieService.addMultiple(movieList).size(),
                "service can add multiple movies"
        );


        Assertions.assertEquals(
                movieList.get(randomInt).getCopiesAvailable(),
                movieService.addMultiple(movieList).get(randomInt).getCopiesAvailable(),
                "and retrieve them reliably"
        );
        randomInt = random.nextInt(4);
        Assertions.assertEquals(
                movieList.get(randomInt).getMovieName(),
                movieService.addMultiple(movieList).get(randomInt).getMovieName()
        );
    }
}
