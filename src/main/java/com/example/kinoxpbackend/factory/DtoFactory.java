package com.example.kinoxpbackend.factory;

import com.example.kinoxpbackend.KinoXpBackendApplication;
import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.movie.DTOs.MovieDTO;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movieTheater.DTOs.MovieTheaterDTO;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory
{
    private static ModelMapper modelMapper = KinoXpBackendApplication.modelMapper();


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

















}
