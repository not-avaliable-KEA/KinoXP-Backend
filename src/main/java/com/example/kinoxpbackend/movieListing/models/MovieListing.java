package com.example.kinoxpbackend.movieListing.models;

import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movieListing")
public class MovieListing {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;


    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "movie_theater_id", nullable = false)
    private MovieTheater movieTheater;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    public MovieListing(Movie movie, MovieTheater movieTheater, LocalDateTime date) {
        this.movie = movie;
        this.movieTheater = movieTheater;
        this.date = date;
    }
}
