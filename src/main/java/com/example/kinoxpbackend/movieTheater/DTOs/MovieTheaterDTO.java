package com.example.kinoxpbackend.movieTheater.DTOs;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieTheaterDTO {

    private long id;
    private String name;
    private int numberOfRows;
    private int numberOfSeats;

}
