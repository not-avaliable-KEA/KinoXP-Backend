package com.example.kinoxpbackend.movieListing.DTO;

import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MovieListingDTO {

    private Long id;
    private Long movieId;
    private Long movieTheaterId;
    private String date;
}
