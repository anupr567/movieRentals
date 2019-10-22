package io.tastycats.movierental.rental.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies(@RequestParam Map<String, String> queryMap) {
        return movieService.findAllMovies(queryMap);
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") String id) {
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
