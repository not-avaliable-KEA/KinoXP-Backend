package com.example.kinoxpbackend.movieListing.DTO;

import com.example.kinoxpbackend.movie.DTOs.MovieDTO;
import com.example.kinoxpbackend.movieTheater.DTOs.MovieTheaterDTO;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieListingDTOTest {

    private Long id;
    private MovieDTO movie;
    private MovieTheaterDTO movieTheater;
    private String date;
}
