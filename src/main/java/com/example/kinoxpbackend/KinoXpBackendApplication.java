package com.example.kinoxpbackend;

import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.services.MovieService;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.service.MovieListingService;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.services.MovieTheaterService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class KinoXpBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(KinoXpBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KinoXpBackendApplication.class, args);
    }


    @Bean
    public CommandLineRunner importData(EmployeeService service) {
        return (args) -> {

            service.create(new Employee("name", "role", "email", "phone",
                    "username", "password", "salt"));
            service.create(new Employee("name", "role", "email", "phone",
                    "username2", "password", "salt"));
            service.create(new Employee("name", "role", "email", "phone",
                    "username3", "lort", "salt"));

            Optional<Employee> optional = service.get(1);
            if (optional.isPresent()) {
                log.info("found employee");
                optional.get().setName("Changed");
                optional.get().setPassword("");

                System.out.println("before = " + optional.get().getSalt());
                System.out.println("after = " + service.update(optional.get()).getSalt());

            } else {
                log.error("did not find employee");
            }

            System.out.println(service.get(1).get().getName());

            log.info("Application ready");
        };
    }*/

/*
 @Bean
    public CommandLineRunner importDataMovie(MovieService service) {
        return (args) -> {

            service.create(new Movie("name1", "Genre", 1.2, "actors",
                    "director", 13, "beskrivelse"));
            service.create(new Movie("name2", "Fantasy", 1.3, "actors",
                    "director", 14, "beskrivelse"));
            service.create(new Movie("name3", "Horror", 1.4, "actors",
                    "director", 15, "beskrivelse"));
            service.create(new Movie("name4", "Romance", 1.5, "actors",
                    "director", 16, "beskrivelse"));

            Optional<Movie> optional = service.get(1);
            if (optional.isPresent()) {
                log.info("found movie");
                optional.get().setName("Changed");

            } else {
                log.error("did not find movie");
            }

            System.out.println(service.get(1).get().getName());

            log.info("Application ready");

        };
    }*/

    @Bean
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /*
   @Bean
    public CommandLineRunner importDataMovieTheatre(MovieTheaterService service) {
        return (args) -> {
            service.create(new MovieTheater("Bio 1 ", 25, 16));
            service.create(new MovieTheater("Bio 2 ", 20, 12));


            Optional<MovieTheater> optional = service.get(1);
            if (optional.isPresent()) {
                log.info("found Movie theatre");
            } else {
                log.info("Did not find the movie theatre");
            }

            System.out.println(service.getAll());

            log.info("Application ready");
        };

    }

    @Bean
    public CommandLineRunner importDataFromMovieListings(MovieListingService movieListingServiceservice, MovieService movieService,
                                                         MovieTheaterService movieTheaterService){
        return args -> {

            movieListingServiceservice.create(new MovieListing(movieService.get(1).get(), movieTheaterService.get(1).get(), LocalDateTime.now()));

        };
    }

 */
}
