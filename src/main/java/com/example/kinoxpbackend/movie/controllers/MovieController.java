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

    @GetMapping(params = "genre")
    public ResponseEntity<List<MovieDTO>> findAllByGenre(@RequestParam String genre){
        System.out.println(genre);
        return ResponseEntity.ok().body(DtoFactory.fromMovies(service.getAllByGenre(genre)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Movie> item = service.get(id);

        if (item.isEmpty()) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(DtoFactory.fromMovie(item.get()));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<Movie> findWithAllInfo(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Movie> item = service.get(id);

        if (item.isEmpty()) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(item.get());
    }

    @PostMapping
    public ResponseEntity<MovieDTO> create(@Valid @RequestBody MovieDTO movieDTO){
        Movie item = service.create(DtoFactory.fromMovieDTO(movieDTO));
        return  ResponseEntity.ok().body(DtoFactory.fromMovie(item));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO movieDTO, @PathVariable("id") Long id){
        movieDTO.setId(id);
        Movie item = service.update(DtoFactory.fromMovieDTO(movieDTO));
        return ResponseEntity.ok().body(DtoFactory.fromMovie(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow( () -> new ResourceNotFoundException("Movie %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }
}
