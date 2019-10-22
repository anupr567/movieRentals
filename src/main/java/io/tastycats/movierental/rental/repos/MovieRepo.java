package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepo extends MongoRepository<Movie, String> {
    public Page<Movie> findAll(Pageable pageable);
    public Page<Movie> findAllByMovieNameContaining(String searchTerm, Pageable pageable);
    public Page<Movie> findAllByGenresContaining(String searchTerm, Pageable pageable);
    public Page<Movie> findAllByCastContaining(String searchTerm, Pageable pageable);
    public Page<Movie> findAllByDirectorNamesContaining(String searchTerm, Pageable pageable);
}
