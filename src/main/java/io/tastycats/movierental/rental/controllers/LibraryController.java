package io.tastycats.movierental.rental.controllers;


import io.tastycats.movierental.rental.models.Library;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.services.LibraryService;
import io.tastycats.movierental.rental.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<Library> getAllBookings() {
        return libraryService.getAllBookings();
    }

    @PostMapping("/rent")
    public Library rentMovie(@RequestParam Map<String, String> requestParam) {
        return libraryService.rentMovie(requestParam);
    }
}
