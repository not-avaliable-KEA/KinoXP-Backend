package com.example.kinoxpbackend.reservations.repositories;

import com.example.kinoxpbackend.reservations.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
