package com.example.kinoxpbackend.movie.DTOs;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {

    private Long id;
    private String name;
    private String genre;
    private double length;
    private String actors;
    private String director;
    private int ageLimit;
}
