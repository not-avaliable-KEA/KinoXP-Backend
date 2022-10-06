package com.example.kinoxpbackend.movie.controllers;

import com.example.kinoxpbackend.factory.DtoFactory;
import com.example.kinoxpbackend.movie.DTOs.MovieDTO;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.services.MovieService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll(){
        return ResponseEntity.ok().body(DtoFactory.fromMovies(service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Movie> item = service.get(id);

        if (item.isEmpty()) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(DtoFactory.fromMovie(item.get()));
    }

    @PostMapping
    public ResponseEntity<MovieDTO> create(@Valid @RequestBody Movie movie){
        Movie item = service.create(movie);
        return  ResponseEntity.ok().body(DtoFactory.fromMovie(item));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody Movie movie, @PathVariable("id") Long id){
        movie.setId(id);
        Movie item = service.update(movie);
        return ResponseEntity.ok().body(DtoFactory.fromMovie(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow( () -> new ResourceNotFoundException("Movie %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body("{deleted: " + delete + "}");
    }
}
