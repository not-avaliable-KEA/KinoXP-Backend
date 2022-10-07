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
    private int numberOfColumns;

    public MovieTheater(String name, int numberOfRows, int numberOfColumns) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public MovieTheater update(MovieTheater other) {
        this.name = other.getName();
        this.numberOfRows = other.getNumberOfRows();
        this.numberOfColumns = other.getNumberOfColumns();
        return this;
    }
}
