package io.tastycats.movierental.rental.controllers;


import io.tastycats.movierental.rental.models.Library;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.services.LibraryService;
import io.tastycats.movierental.rental.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/booking")
public class LibraryController {
    private static Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<Library> getAllBookings() {
        return libraryService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Library getBookingById(@PathVariable("id") String id) {
        return libraryService.getBookingById(id);
    }

    @PostMapping("/rent")
    public Library rentMovie(@RequestParam Map<String, String> requestParam) {
        return libraryService.rentMovie(requestParam);
    }

    @PostMapping("/return/{id}")
    public Library returnMovie(@PathVariable("id") String id) {
        try {
            Movie movie = movieService.getMovieById(id);
        } catch(Exception e){
            logger.error("No Such Movie Id exist to get Movie by Id");
        }
        return libraryService.returnMovie(id);
    }

    @PostMapping("/extend/{id}")
    public @ResponseBody
    ResponseEntity<Library> extendBooking() {
        try {
            ResponseEntity<Library> responseEntity = new ResponseEntity<Library>(new Library(), HttpStatus.CREATED);
        } catch(Exception e){
            logger.error("No Booking exist to extend Booking by Id");
        }
        return new ResponseEntity<Library>(new Library(), HttpStatus.CREATED);
    }
}
