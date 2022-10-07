package com.example.kinoxpbackend.movieTheater.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
