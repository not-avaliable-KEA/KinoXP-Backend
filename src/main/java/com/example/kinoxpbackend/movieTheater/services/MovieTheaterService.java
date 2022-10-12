package com.example.kinoxpbackend.movieTheater.services;



import com.example.kinoxpbackend.factory.DtoFactory;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.repositories.MovieTheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieTheaterService {

    MovieTheaterRepository repository;

    public MovieTheaterService(MovieTheaterRepository repository) {
        this.repository = repository;
        DtoFactory.setMovieTheaterService(this);
    }

    public MovieTheater create(MovieTheater movieTheater) {
        return repository.save(movieTheater);
    }


    public List<MovieTheater> getAll() {
        return repository.findAll();
    }

    public Optional<MovieTheater> get(long id){
        return repository.findById(id);
    }

    public MovieTheater update(MovieTheater movieTheater) {
        Optional<MovieTheater> optionalMovieTheater = get(movieTheater.getId());

        if (optionalMovieTheater.isEmpty()) return null;
        else {
            optionalMovieTheater.get().update(movieTheater);
        }
        return repository.save(optionalMovieTheater.get());
    }

    public Boolean delete (long id){
        Optional<MovieTheater> optionalMovieTheater = get(id);

        if (optionalMovieTheater.isPresent()){
            repository.delete(optionalMovieTheater.get());
            return true;
        } else {
            return false;
        }
    }
}
