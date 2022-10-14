package com.example.kinoxpbackend.reservations.services;

import com.example.kinoxpbackend.reservations.models.Reservation;
import com.example.kinoxpbackend.reservations.repositories.ReservationRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository repository;

    public Reservation create(Reservation reservation) {
        return repository.save(reservation);
    }

    public Optional<Reservation> get(Long id) {
        return repository.findById(id);
    }

    public List<Reservation> getAll() {
        return repository.findAll();
    }

    public Reservation update(Reservation reservation) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("update");
    }

    public boolean delete(Long id) {
        Optional<Reservation> reservation = get(id);

        if (reservation.isPresent()){
            repository.delete(reservation.get());
            return true;
        } else {
            return false;
        }
    }
}
