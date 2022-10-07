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
    private int rows;
    private int columns;

    public MovieTheater(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
    }

    public MovieTheater update(MovieTheater other) {
        this.name = other.getName();
        this.rows = other.getRows();
        this.columns = other.getColumns();
        return this;
    }
}
