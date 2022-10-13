package com.example.kinoxpbackend.movie.models;

import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "MOVIES")
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "LENGTH")
    private double length;

    @Column(name = "ACTORS")
    private String actors;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "AGELIMIT")
    private int ageLimit;

    @Column(name = "DESCRIPTION")
    @Lob //get a long text in the DB
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    private List<MovieListing> movieListings;

    public Movie(String name, String genre, double length, String actors, String director, int ageLimit, String description) {
        this.name = name;
        this.genre = genre;
        this.length = length;
        this.actors = actors;
        this.director = director;
        this.ageLimit = ageLimit;
        this.description = description;

    }

    public Movie update(Movie movie){
        this.name = movie.getName();
        this.genre = movie.getGenre();
        this.length = movie.getLength();
        this.actors = movie.getActors();
        this.director = movie.getDirector();
        this.ageLimit = movie.getAgeLimit();
        return this;
    }
}
