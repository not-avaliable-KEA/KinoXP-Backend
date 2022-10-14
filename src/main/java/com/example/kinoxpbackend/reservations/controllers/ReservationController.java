package com.example.kinoxpbackend.reservations.controllers;

import com.example.kinoxpbackend.reservations.models.Reservation;
import com.example.kinoxpbackend.reservations.services.ReservationService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/reservations")
public class ReservationController {

    @Autowired
    ReservationService service;

    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {

        return ResponseEntity.ok().body(service.create(reservation));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findALl(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    //pathvariable = endpoint der identificerer en entity med en primary key.
    public ResponseEntity<Reservation> find(@PathVariable("id") long id) throws ResourceNotFoundException {
        //finder id
        Optional<Reservation> item = service.get(id);
        if(item.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return ResponseEntity.ok().body(item.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> update(@Valid @RequestBody Reservation reservation, @PathVariable("id") Long id) throws ExecutionControl.NotImplementedException {
        reservation.setId(id);
        return ResponseEntity.ok().body(service.update(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }

}
