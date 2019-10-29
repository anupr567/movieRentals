package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LibraryRepo extends MongoRepository<Library, String> {
    public List<Library> findAll();
    public Optional<Library> findBookingByUserId(String userId);
    public Optional<Library> findBookingByMovieId(String MovieId);
}
