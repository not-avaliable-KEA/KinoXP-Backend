package com.example.kinoxpbackend.movieListing.service;

import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.repositories.MovieListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieListingService {

    @Autowired
    private MovieListingRepository repository;

    public MovieListing create(MovieListing movieListing){
        return repository.save(movieListing);
    }

    public List<MovieListing> getAll(){
        return repository.findAll();
    }

    public Optional<MovieListing> get(long id){
        return repository.findById(id);
    }

    public MovieListing update(MovieListing movieListing){
        return repository.save(movieListing);
    }
    public boolean delete(long id) {
        //vi skal have fat i movielisting.
        //optional = container/wrapper til at håndtere om der findes noget, og kan derfor bruges til at returnere en boolean.

        Optional<MovieListing> optionalMovieListing= get(id);

        //check om den eksisterer /bliver der returnet noget.
        //så hvis der bliver returneret noget skal der kaldes delete funktionen.
        //så hvis der ikke bliver returneret en movieslisting skal der returneres false,

        if(optionalMovieListing.isPresent()){
            repository.delete(optionalMovieListing.get());
            return true;
        } else{
            return false;
        }
    }

}
