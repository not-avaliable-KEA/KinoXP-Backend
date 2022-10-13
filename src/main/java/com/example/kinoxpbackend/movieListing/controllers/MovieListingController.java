package com.example.kinoxpbackend.movieListing.controllers;


import com.example.kinoxpbackend.factory.DtoFactory;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTO;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTOTest;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.service.MovieListingService;
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
    public ResponseEntity<MovieListingDTO> create(@Valid @RequestBody MovieListingDTO movieListingDTO) {

        MovieListing movieListing = DtoFactory.fromMovieListingDTO(movieListingDTO);

        return ResponseEntity.ok().body(DtoFactory.fromMovieListing(service.create(movieListing)));
    }

    @GetMapping
    public ResponseEntity<List<MovieListingDTO>> findALl(){
        return ResponseEntity.ok().body(DtoFactory.fromMovieListings(service.getAll()));
    }

    @GetMapping("/{id}")
    //pathvariable = endpoint der identificerer en entity med en primary key.
    public ResponseEntity<MovieListingDTO> find(@PathVariable("id") long id) throws ResourceNotFoundException{
        //finder id
        Optional<MovieListing> item = service.get(id);
        if(item.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return ResponseEntity.ok().body(DtoFactory.fromMovieListing(item.get()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieListingDTO> update(@Valid @RequestBody MovieListingDTO movieListingDTO, @PathVariable("id") Long id){
        movieListingDTO.setId(id);
        MovieListing movieListingItem = service.update(DtoFactory.fromMovieListingDTO(movieListingDTO));
        return ResponseEntity.ok().body(DtoFactory.fromMovieListing(movieListingItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }

    //cam test
    @GetMapping("/test")
    public ResponseEntity<List<MovieListingDTOTest>> test(){
      return ResponseEntity.ok().body(DtoFactory.fromMovieListingsTest(service.getAll()));

    }

}


