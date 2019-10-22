package io.tastycats.movierental.rental.services;


import io.tastycats.movierental.rental.models.Library;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.repos.LibraryRepo;
import io.tastycats.movierental.rental.repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LibraryService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private LibraryRepo libraryRepo;

    public Library rentMovie(Map<String, String> requestParam) {
        if (requestParam.containsKey("uid") && requestParam.containsKey("mid")) {
            Library newRent = new Library();
            String movieId = requestParam.get("mid");
            String userId = requestParam.get("uid");

            Movie movieToBook = movieRepo.findById(movieId).orElse(new Movie());
            movieToBook.setCopiesAvailable(movieToBook.getCopiesAvailable() - 1);
            newRent.setMovieId(movieToBook.getId());
            newRent.setUserId(userId);
            newRent.setBookingDate(LocalDate.now());
            newRent.setReturnDate(LocalDate.now().plusDays(14));

            movieRepo.save(movieToBook);
            return libraryRepo.save(newRent);

        }
        return null;
    }

    public List<Library> getAllBookings() {
        return libraryRepo.findAll();
    }
}
