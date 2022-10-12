package com.example.kinoxpbackend.movieListing.repositories;

import com.example.kinoxpbackend.movieListing.models.MovieListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieListingRepository extends JpaRepository<MovieListing, Long> {


}
