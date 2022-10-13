package com.example.kinoxpbackend.movieTheater.models;

import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MovieTheaters")
@Getter
@Setter
@NoArgsConstructor
public class MovieTheater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private int numberOfRows;
    private int numberOfSeats;

    @JsonBackReference
    //JSONbackreference tager ikke en listen med movielistings,
    @OneToMany(mappedBy = "movieTheater", cascade = CascadeType.REMOVE)
    private List<MovieListing> movieListings;

    public MovieTheater(String name, int numberOfRows, int numberOfSeats) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.numberOfSeats = numberOfSeats;
    }

    public MovieTheater update(MovieTheater other) {
        this.name = other.getName();
        this.numberOfRows = other.getNumberOfRows();
        this.numberOfSeats = other.getNumberOfSeats();
        return this;
    }
}
