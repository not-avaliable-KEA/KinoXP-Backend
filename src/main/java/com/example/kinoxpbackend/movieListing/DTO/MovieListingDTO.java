package com.example.kinoxpbackend.movieListing.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieListingDTO {

    private Long id;
    private Long movieId;
    private Long movieTheaterId;
    private String date;
}
