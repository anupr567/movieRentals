package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LibraryRepo extends MongoRepository<Library, String> {
    public List<Library> findAll();
    public List<Library> findByUserId(String userId);
    public List<Library> findByMovieId(String MovieId);
}
