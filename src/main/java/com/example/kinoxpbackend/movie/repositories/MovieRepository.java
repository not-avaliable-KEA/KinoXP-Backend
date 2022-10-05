package com.example.kinoxpbackend.movie.repositories;

import com.example.kinoxpbackend.movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
