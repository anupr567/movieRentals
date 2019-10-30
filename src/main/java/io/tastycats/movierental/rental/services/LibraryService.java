package io.tastycats.movierental.rental.services;


import io.tastycats.movierental.rental.models.Library;
import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.models.User;
import io.tastycats.movierental.rental.repos.LibraryRepo;
import io.tastycats.movierental.rental.repos.MovieRepo;
import io.tastycats.movierental.rental.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LibraryService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private LibraryRepo libraryRepo;

    @Autowired
    private UserRepo userRepo;

    public Library rentMovie(Map<String, String> requestParam) {
        if (requestParam.containsKey("uid") && requestParam.containsKey("mid")) {
            Library newRent = new Library();
            String movieId = requestParam.get("mid");
            String userId = requestParam.get("uid");

            Movie movieToBook = movieRepo.findById(movieId).get();
            User userToBookFor = userRepo.findById(userId).get();
            movieToBook.setCopiesAvailable(movieToBook.getCopiesAvailable() - 1);
            newRent.setMovieId(movieToBook.getId());
            newRent.setUserId(userId);
            newRent.setBookingDate(LocalDate.now());
            newRent.setReturnDate(LocalDate.now().plusDays(14));

            movieRepo.save(movieToBook);
            Library newBooking =  libraryRepo.save(newRent);
            userToBookFor.getPresentBookingIds().add(newBooking.getId());
            userRepo.save(userToBookFor);
            return newBooking;
        }
        return null;
    }

    public List<Library> getAllBookings() {
        return libraryRepo.findAll();
    }
    public List<Library> getByUserId(String userId){
        return libraryRepo.findByUserId(userId);
    }
    public List<Library> getByMovieId(String movieId ){
        return libraryRepo.findByMovieId(movieId);
    }
    public Library returnMovie(String bookingId) {
        Library booking = libraryRepo.findById(bookingId).get();
        String movieId = booking.getMovieId();
        String userId = booking.getUserId();

        User userReturning = userRepo.findById(userId).get();
        Movie movieReturned = movieRepo.findById(movieId).get();

        movieReturned.setCopiesAvailable(movieReturned.getCopiesAvailable() + 1);
        movieRepo.save(movieReturned);
        userReturning.getPresentBookingIds().remove(bookingId);
        userReturning.getHistoryBookingIds().add(bookingId);

        long dateDifference = ChronoUnit.DAYS.between(booking.getReturnDate(), LocalDate.now());
//        dateDifference.

        if (dateDifference > 0) {
            // late return
            userReturning.setFine((int)dateDifference * 10);
        }
        userRepo.save(userReturning);

        libraryRepo.delete(booking);

        return booking;
    }

    public Library getBookingById(String id) {
        return libraryRepo.findById(id).orElse(null);
    }
}
