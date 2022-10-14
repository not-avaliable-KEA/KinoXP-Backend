package com.example.kinoxpbackend.factory;

import com.example.kinoxpbackend.KinoXpBackendApplication;
import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.movie.DTOs.MovieDTO;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.services.MovieService;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTO;
import com.example.kinoxpbackend.movieListing.DTO.MovieListingDTOTest;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.service.MovieListingService;
import com.example.kinoxpbackend.movieTheater.DTOs.MovieTheaterDTO;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.services.MovieTheaterService;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory
{
    private static ModelMapper modelMapper = KinoXpBackendApplication.modelMapper();

    private static MovieService movieService;

    private static MovieTheaterService movieTheaterService;

    private static MovieListingService movieListingService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public static void setMovieService(MovieService movieService) {
        DtoFactory.movieService = movieService;
    }

    public static void setMovieTheaterService(MovieTheaterService movieTheaterService) {
        DtoFactory.movieTheaterService = movieTheaterService;
    }

    public static void setMovieListingService(MovieListingService movieListingService) {
        DtoFactory.movieListingService = movieListingService;
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

    public static Movie fromMovieDTO(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

    /*
        MovieTheater
     */

    public static MovieTheaterDTO fromMovieTheater(MovieTheater movieTheater)
    {
        return modelMapper.map(movieTheater, MovieTheaterDTO.class);
    }

    public static List<MovieTheaterDTO> fromMovieTheaters(List<MovieTheater> movieTheaters){
        return movieTheaters.stream()
                .map(movieTheater -> modelMapper.map(movieTheater, MovieTheaterDTO.class))
                .collect(Collectors.toList());
    }

    public static MovieTheater fromMovieTheaterDTO(MovieTheaterDTO movieTheaterDTO) {
        return modelMapper.map(movieTheaterDTO, MovieTheater.class);
    }

    /*
        MovieListing
     */

    public static MovieListingDTO fromMovieListing(MovieListing movieListing) {
        MovieListingDTO dto = modelMapper.map(movieListing, MovieListingDTO.class);

        LocalDateTime timeUnformated = LocalDateTime.parse(dto.getDate());

        dto.setDate(timeUnformated.format(formatter));

        return dto;
    }

    public static List<MovieListingDTO> fromMovieListings(List<MovieListing> movieListings){
        return movieListings.stream()
                .map(DtoFactory::fromMovieListing)
                .collect(Collectors.toList());
    }

    public static MovieListing fromMovieListingDTO(MovieListingDTO movieListingDTO) {
        // oprette ny tom movieListing
        MovieListing movieListing = new MovieListing();

        // overføre id og date dertil.
        movieListing.setId(movieListingDTO.getId());

        LocalDateTime dateTime = LocalDateTime.parse(movieListingDTO.getDate(), formatter);
        movieListing.setDate(dateTime);

        // finde movie udefra movie id, og overføre til movielisting.
        movieListing.setMovie(movieService.get(movieListingDTO.getMovieId()).get());

        // gør det samme med movie theater.
        movieListing.setMovieTheater(movieTheaterService.get(movieListingDTO.getMovieTheaterId()).get());

        //returnerer movielisting.
        return movieListing;
    }
    //cam test
    public static MovieListingDTOTest fromMovieListingTest(MovieListing movieListing){
        MovieListingDTOTest dto = modelMapper.map(movieListing, MovieListingDTOTest.class);

        LocalDateTime timeUnformated = LocalDateTime.parse(dto.getDate());

        dto.setDate(timeUnformated.format(formatter));

        return dto;
    }

    public static List<MovieListingDTOTest> fromMovieListingsTest(List<MovieListing> movieListings){
        return movieListings.stream()
                .map(DtoFactory::fromMovieListingTest)
                .collect(Collectors.toList());
    }
}
