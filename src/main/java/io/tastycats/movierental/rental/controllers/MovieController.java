package io.tastycats.movierental.rental.controllers;


//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModelProperty;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies(@RequestParam Map<String, String> queryMap) {
        return movieService.findAllMovies(queryMap);
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") String id) {
        try {
            Movie movie = movieService.getMovieById(id);
        } catch(Exception e){
            logger.error("No Such Movie Id exist to get Movie by Id");
        }
        return movieService.getMovieById(id);
    }

    @PostMapping("/addone")
    public Movie addMovie(@RequestBody Movie movieToAdd) {
        return movieService.addMovie(movieToAdd);
    }

    @PostMapping("/addmultiple")
    public List<Movie> addMultiple(@RequestBody List<Movie> moviesToAdd) {
        return movieService.addMultiple(moviesToAdd);
    }

    @GetMapping(value = "/search")
    public
    List<Movie> searchStudents(@RequestParam Map<String, String> requestParams) {
        return movieService.searchMovie(requestParams);
    }
}
