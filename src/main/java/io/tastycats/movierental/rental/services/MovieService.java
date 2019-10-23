package io.tastycats.movierental.rental.services;

import io.tastycats.movierental.rental.models.Movie;
import io.tastycats.movierental.rental.repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    private Pageable getPageable(Map<String, String> queryMap) {
        String sortKey = "movieName";
        int pageLength = 10;
        int page = 0;
        if (queryMap.containsKey("sort")) {
            sortKey = queryMap.get("sort");
        }
        if (queryMap.containsKey("pagesize")) {
            pageLength = Integer.parseInt(queryMap.get("pagesize"));
        }
        if (queryMap.containsKey("page")) {
            page = Integer.parseInt(queryMap.get("page"));
        }

        return PageRequest.of(page, pageLength, Sort.by(sortKey));
    }

    public List<Movie> findAllMovies(Map<String, String> queryMap) {
        return movieRepo.findAll(getPageable(queryMap)).getContent();
    }

    public Movie addMovie(Movie movieToAdd) {
        return movieRepo.save(movieToAdd);
    }

    public List<Movie> addMultiple(List<Movie> moviesToAdd) {
        return movieRepo.saveAll(moviesToAdd);
    }

    public List<Movie> searchMovie(Map<String, String> queryMap) {
        if (queryMap.containsKey("name")) {
            return movieRepo.findAllByMovieNameContaining(queryMap.get("name"), getPageable(queryMap)).getContent();
        }
        if (queryMap.containsKey("genre")) {
            return movieRepo.findAllByGenresContaining(queryMap.get("genre"), getPageable(queryMap)).getContent();
        }
        if (queryMap.containsKey("cast")) {
            return movieRepo.findAllByCastContaining(queryMap.get("cast"), getPageable(queryMap)).getContent();
        }
        if (queryMap.containsKey("director")) {
            return movieRepo.findAllByDirectorNamesContaining(queryMap.get("director"), getPageable(queryMap)).getContent();
        }
        return new ArrayList<>();
    }

    public Movie getMovieById(String id) {
        return movieRepo.findById(id).get();
    }

}
