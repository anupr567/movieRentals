package io.tastycats.movierental.rental.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "bookings")
public class Library {
    @Id
    private String id;

    private String movieId;
    private LocalDate bookingDate;
    private LocalDate returnDate;
    private String userId;
}
