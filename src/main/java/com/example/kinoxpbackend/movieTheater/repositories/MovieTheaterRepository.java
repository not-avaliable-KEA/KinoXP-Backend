package com.example.kinoxpbackend.movieTheater.repositories;

import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Long> {

}
