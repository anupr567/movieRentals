package io.tastycats.movierental.rental.controllers;


import io.tastycats.movierental.rental.models.Library;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.services.LibraryService;
import io.tastycats.movierental.rental.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/return/{id}")
    public Library returnMovie(@PathVariable("id") String id) {
        return libraryService.returnMovie(id);
    }

    @PostMapping("/extend/{id}")
    public @ResponseBody
    ResponseEntity<Library> extendBooking() {
        return new ResponseEntity<Library>(new Library(), HttpStatus.CREATED);
    }
}
