package com.example.kinoxpbackend.movieTheater.controllers;


import com.example.kinoxpbackend.factory.DtoFactory;
import com.example.kinoxpbackend.movieTheater.DTOs.MovieTheaterDTO;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.services.MovieTheaterService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/movietheaters")
public class MovieTheaterController {

    private final MovieTheaterService service;

    public MovieTheaterController(MovieTheaterService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<MovieTheaterDTO>> findAll() {
        return ResponseEntity.ok().body(DtoFactory.fromMovieTheaters(service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieTheaterDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<MovieTheater> item = service.get(id);

        if (item.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(DtoFactory.fromMovieTheater(item.get()));
    }

    @PostMapping
    public ResponseEntity<MovieTheaterDTO> create(@Valid @RequestBody MovieTheaterDTO movieTheaterDTO) {
        MovieTheater item = service.create(DtoFactory.fromMovieTheaterDTO(movieTheaterDTO));
        return ResponseEntity.ok().body(DtoFactory.fromMovieTheater(item));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieTheaterDTO> update(@Valid @RequestBody MovieTheaterDTO movieTheaterDTO, @PathVariable("id") Long id) {
        movieTheaterDTO.setId(id);
        MovieTheater item = service.update(DtoFactory.fromMovieTheaterDTO(movieTheaterDTO));
        return ResponseEntity.ok().body(DtoFactory.fromMovieTheater(item));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow(() -> new ResourceNotFoundException("MovieTheatre %d not found".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }


}
