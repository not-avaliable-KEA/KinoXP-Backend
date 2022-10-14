package com.example.kinoxpbackend.reservations.models;

import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table()
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int amountOfSeats;

    private boolean meetsAgeRequirement;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private MovieListing movieListing;

    public Reservation(String name, int amountOfSeats, boolean meetsAgeRequirement, MovieListing movieListing) {
        this.name = name;
        this.amountOfSeats = amountOfSeats;
        this.meetsAgeRequirement = meetsAgeRequirement;
        this.movieListing = movieListing;
    }
}
