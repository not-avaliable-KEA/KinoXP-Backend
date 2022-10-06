package com.example.kinoxpbackend.movie.services;


import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository repository;

    public Movie create(Movie movie){
        return repository.save(movie);
    }

    public Optional<Movie> get(long id){
        return repository.findById(id);
    }

    public List<Movie> getAll(){
        return repository.findAll();
    }

    public Movie update(Movie movie){

        Optional<Movie> optionalMovie = get(movie.getId());

        if (optionalMovie.isEmpty()) return null;
        else {
            optionalMovie.get().update(movie);
        }
        return repository.save(optionalMovie.get());
    }

    public Boolean delete(long id){
        Optional<Movie> optionalMovie = get(id);

        if (optionalMovie.isPresent()){
            repository.delete(optionalMovie.get());
            return true;
        } else {
            return false;
        }
    }
}
