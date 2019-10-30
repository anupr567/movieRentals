package io.tastycats.movierental.rental.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private List<String> presentBookingIds = new ArrayList<>();
    private List<String> historyBookingIds = new ArrayList<>();
    private int fine = 0;
    private String dpUrl;

//    @Indexed(unique = true)
//    private String userName;
//    private String password;
//    private String role;
    List<Movie> wishList = new ArrayList<>();
    public void addMovieToWishList(Movie movie) {
        wishList.add(movie);
    }
}
