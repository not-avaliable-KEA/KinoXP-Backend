package com.example.kinoxpbackend.movieListing.controllers;


import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTO;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.service.MovieListingService;
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
@RequestMapping("api/v1/movieListings")
public class MovieListingController {

    //deklarerer og encapser MovieListingService og final= er non-access-modifer- man creater en konstant variable der ikke ændrer sig.
    private final MovieListingService service;

    //depencies injektion, man injekter de afhængigheder som classen skal have.
    public MovieListingController(MovieListingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovieListing> create(@Valid @RequestBody MovieListing movieListing) {
        MovieListing movieListings = service.create(movieListing);
        return ResponseEntity.ok().body(movieListings);
    }

    @GetMapping
    public ResponseEntity<List<MovieListing>> findALl(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PatchMapping("{id}")
    public ResponseEntity<MovieListing> update(@Valid @RequestBody MovieListing movieListing, @PathVariable("id") Long id){
        movieListing.setId(id);
        MovieListing movieListingItem = service.update(movieListing);
        return ResponseEntity.ok().body(service.update(movieListingItem));
    }

    @GetMapping("/{id}")
    //pathvariable = endpoint der identificerer en entity med en primary key.
    public ResponseEntity<MovieListing> find(@PathVariable("id") long id) throws ResourceNotFoundException{
        //finder id
        Optional<MovieListing> item = service.get(id);
        if(item.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); return ResponseEntity.ok().body(item.get());
    }





}


