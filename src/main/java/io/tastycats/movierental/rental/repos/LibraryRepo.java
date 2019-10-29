package io.tastycats.movierental.rental.repos;

import io.tastycats.movierental.rental.models.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryRepo extends MongoRepository<Library, String> {
}
