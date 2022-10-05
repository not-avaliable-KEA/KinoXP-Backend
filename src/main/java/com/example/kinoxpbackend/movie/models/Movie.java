package com.example.kinoxpbackend.movie.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

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
    private Time length;

    @Column(name = "ACTORS")
    private String actors;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "AGELIMIT")
    private int ageLimit;

    public Movie(Long id, String name, String genre, Time length, String actors, String director, int ageLimit) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.length = length;
        this.actors = actors;
        this.director = director;
        this.ageLimit = ageLimit;
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
