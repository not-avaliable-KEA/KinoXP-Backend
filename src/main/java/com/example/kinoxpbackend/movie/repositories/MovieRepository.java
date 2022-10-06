package com.example.kinoxpbackend.movie.repositories;

import com.example.kinoxpbackend.movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByGenre(String genre);
}
