package io.tastycats.movierental.rental.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "library")
public class Movie {
    @Id
    private String id;

    private String movieName;
    private List<String> directorNames;
    private List<String> cast;
    private List<String> genres;

    private float rating; // out of 10
    private float popularity; // out of 5

    private int releaseYear;
    private int releaseMonth;
    private int releaseDay;

    private int copiesAvailable;
}
