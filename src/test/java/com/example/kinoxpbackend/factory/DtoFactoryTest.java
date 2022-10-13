package com.example.kinoxpbackend.factory;


import com.example.kinoxpbackend.movie.DTOs.MovieDTO;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.services.MovieService;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTO;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.service.MovieListingService;
import com.example.kinoxpbackend.movieTheater.DTOs.MovieTheaterDTO;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.services.MovieTheaterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DtoFactoryTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieTheaterService movieTheaterService;

    @Test
    void fromMovieDTO() {
        // arrange
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setName("name");
        movieDTO.setActors("actors");
        movieDTO.setDirector("director");
        movieDTO.setGenre("genre");
        movieDTO.setLength(100);
        movieDTO.setDescription("desc");
        movieDTO.setAgeLimit(1);

        // act
        Movie result = DtoFactory.fromMovieDTO(movieDTO);

        // assert
        assertEquals(movieDTO.getId(), result.getId());
        assertEquals(movieDTO.getName(), result.getName());
        assertEquals(movieDTO.getActors(), result.getActors());
        assertEquals(movieDTO.getDirector(), result.getDirector());
        assertEquals(movieDTO.getGenre(), result.getGenre());
        assertEquals(movieDTO.getLength(), result.getLength());
        assertEquals(movieDTO.getDescription(), result.getDescription());
        assertEquals(movieDTO.getAgeLimit(), result.getAgeLimit());
        assertNull(result.getMovieListings());
    }

    @Test
    void fromMovieTheaterDTO() {
        // arrange
        MovieTheaterDTO movieTheaterDTO = new MovieTheaterDTO();
        movieTheaterDTO.setId(1);
        movieTheaterDTO.setName("name");
        movieTheaterDTO.setNumberOfRows(10);
        movieTheaterDTO.setNumberOfSeats(10);

        // act
        MovieTheater result = DtoFactory.fromMovieTheaterDTO(movieTheaterDTO);

        // arrange
        assertEquals(movieTheaterDTO.getId(), result.getId());
        assertEquals(movieTheaterDTO.getName(), result.getName());
        assertEquals(movieTheaterDTO.getNumberOfRows(), result.getNumberOfRows());
        assertEquals(movieTheaterDTO.getNumberOfSeats(), result.getNumberOfSeats());
        assertNull(result.getMovieListings());
    }

    @Test
    void fromMovieListingDTO() {
        // arrange
        movieService.create(new Movie("movie", "genre", 1, "actors",
                "director", 1, "desc"));
        movieTheaterService.create(new MovieTheater("theater", 10, 10));

        MovieListingDTO movieListingDTO = new MovieListingDTO();
        movieListingDTO.setId(1L);
        movieListingDTO.setMovieId(1L);
        movieListingDTO.setMovieTheaterId(1L);
        movieListingDTO.setDate("2000-01-01 10:10");

        // act
        MovieListing result = DtoFactory.fromMovieListingDTO(movieListingDTO);

        // assert
        assertEquals(movieListingDTO.getId(), result.getId());
        assertEquals(movieService.get(1).get().getId(), result.getMovie().getId());
        assertEquals(movieService.get(1).get().getName(), result.getMovie().getName());
        assertEquals(movieTheaterService.get(1).get().getId(), result.getMovieTheater().getId());
        assertEquals(movieTheaterService.get(1).get().getName(), result.getMovieTheater().getName());
        assertEquals(LocalDateTime.of(2000, 1, 1, 10, 10), result.getDate());
    }

    @Test
    void fromMovieListing() {
        // arrange
        movieService.create(new Movie("movie", "genre", 1, "actors",
                "director", 1, "desc"));
        movieTheaterService.create(new MovieTheater("theater", 10, 10));
        MovieListing movieListing = new MovieListing(movieService.get(1).get(),
                                                    movieTheaterService.get(1).get(),
                                                    LocalDateTime.of(2000, 1, 1, 10, 10));

        // act
        MovieListingDTO result = DtoFactory.fromMovieListing(movieListing);

        // assert
        assertEquals(1, result.getMovieId());
        assertEquals(1, result.getMovieTheaterId());
        assertEquals("2000-01-01 10:10", result.getDate());
    }
}