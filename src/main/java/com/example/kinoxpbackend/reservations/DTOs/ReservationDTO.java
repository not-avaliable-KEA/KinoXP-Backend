package com.example.kinoxpbackend.reservations.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {

    private long id;

    private String name;

    private int amountOfSeats;

    private boolean meetsAgeRequirement;

    private long movieListingId;
}
