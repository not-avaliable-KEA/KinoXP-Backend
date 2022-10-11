package com.example.kinoxpbackend.factory;

import com.example.kinoxpbackend.KinoXpBackendApplication;
import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.movie.DTOs.MovieDTO;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.services.MovieService;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTO;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieTheater.DTOs.MovieTheaterDTO;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.services.MovieTheaterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory
{
    private static ModelMapper modelMapper = KinoXpBackendApplication.modelMapper();

    private static MovieService movieService;

    private static MovieTheaterService movieTheaterService;

    public static void setMovieService(MovieService movieService) {
        DtoFactory.movieService = movieService;
    }

    public static void setMovieTheaterService(MovieTheaterService movieTheaterService) {
        DtoFactory.movieTheaterService = movieTheaterService;
    }

    /*
            Employees
        */
    public static EmployeeDTO fromEmployee(Employee employee)
    {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee fromEmployeeDto(EmployeeDTO dto)
    {
        return modelMapper.map(dto, Employee.class);
    }

    public static List<EmployeeDTO> fromEmployees(List<Employee> employees)
    {
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }


    /*
        Movies
    */

    public static MovieDTO fromMovie(Movie movie)
    {
        return modelMapper.map(movie, MovieDTO.class);
    }

    public static List<MovieDTO> fromMovies(List<Movie> movies){
        return movies.stream().map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public static MovieTheaterDTO fromMovieTheater(MovieTheater movieTheater)
    {
        return modelMapper.map(movieTheater, MovieTheaterDTO.class);
    }

    public static List<MovieTheaterDTO> fromMovieTheaters(List<MovieTheater> movieTheaters){
        return movieTheaters.stream()
                .map(movieTheater -> modelMapper.map(movieTheater, MovieTheaterDTO.class))
                .collect(Collectors.toList());
    }

    public static MovieListingDTO fromMovieListing(MovieListing movieListing)
    {
        return modelMapper.map(movieListing, MovieListingDTO.class);
    }

    public static List<MovieListingDTO> fromMovieListings(List<MovieListing> movieListings){
        return movieListings.stream()
                .map(movieListing -> modelMapper.map(movieListing, MovieListingDTO.class))
                .collect(Collectors.toList());
    }

    public static MovieListing fromMovieListingDTO(MovieListingDTO movieListingDTO) {
    // oprette ny tom movielisting
        MovieListing movieListing = new MovieListing();

        // overføre id og date dertil.
        movieListing.setId(movieListingDTO.getId());
        //movieListing.setDate(Date.from(movieListingDTO.getDate()));

        // finde movie udefra movie id, og overføre til movielisting.

        movieListing.setMovie(movieService.get(movieListingDTO.getMovieId()).get());

        //det samme med movie theater.
        movieListing.setMovieTheater(movieTheaterService.get(movieListingDTO.getMovieTheaterId()).get());

        //returnerer movielisting.
        return movieListing;
    }

}
