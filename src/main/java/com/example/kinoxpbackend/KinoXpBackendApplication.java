package com.example.kinoxpbackend;

import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import com.example.kinoxpbackend.movie.models.Movie;
import com.example.kinoxpbackend.movie.services.MovieService;
import com.example.kinoxpbackend.movieListing.models.MovieListing;
import com.example.kinoxpbackend.movieListing.service.MovieListingService;
import com.example.kinoxpbackend.movieTheater.models.MovieTheater;
import com.example.kinoxpbackend.movieTheater.services.MovieTheaterService;
import com.example.kinoxpbackend.reservations.models.Reservation;
import com.example.kinoxpbackend.reservations.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class KinoXpBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(KinoXpBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KinoXpBackendApplication.class, args);
    }

/*
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

            service.create(new Movie("Hobbitten: En uventet rejse", "Fantasy", 169, " Martin Freeman, Ian McKellen, Richard Armitage, Andy Serkis",
                    "Peter Jackson", 11, "A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug."));
            service.create(new Movie("Hobbitten: Dragen Smaugs ødemark", "Fantasy", 161, "Ian McKellen, Martin Freeman, Richard Armitage, Ken Stott",
                    "Peter Jackson", 11, "The dwarves, along with Bilbo Baggins and Gandalf the Grey, continue their quest to reclaim Erebor, their homeland, from Smaug. Bilbo Baggins is in possession of a mysterious and magical ring."));
            service.create(new Movie("Hobbitten: Femhæreslaget", "Fantasy", 144, "Ian McKellen, Martin Freeman, Richard Armitage, Cate Blanchett",
                    "Peter Jackson", 11, "Bilbo and company are forced to engage in a war against an array of combatants and keep the Lonely Mountain from falling into the hands of a rising darkness."));
            service.create(new Movie("Bullet Train", "Action", 127, "Brad Pitt, Joey King, Aaron Taylor-Johnson",
                    "David Leitch", 15, "Five assassins aboard a swiftly-moving bullet train to find out that their missions have something in common."));
            service.create(new Movie("Vesper", "Sci-Fi", 114, "Raffiella Chapman, Eddie Marsan, Rosy McEwen, Richard Brakectors",
                    "Kristina Buozyte, Bruno Samper", 15, "Struggling to survive with her father after the collapse of Earth's ecosystem, 13-year-old Vesper must use her wits, strength and bio-hacking abilities to fight for the future."));
            service.create(new Movie("Blonde", "Romance", 167, "Ana de Armas, Lily Fisher, Julianne Nicholson, Tygh Runyan",
                    "Andrew Dominik", 17, "A fictionalized chronicle of the inner life of Marilyn Monroe."));


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

    }*/

    /*
    @Bean
    public CommandLineRunner importDataFromMovieListings(MovieListingService movieListingServiceservice,
                                                         MovieService movieService,
                                                         MovieTheaterService movieTheaterService){
        return args -> {

            movieListingServiceservice.create(new MovieListing(movieService.get(1).get(), movieTheaterService.get(1).get(), LocalDateTime.now().plusDays(1)));

        };
    }*/

    /*
    @Bean
    public CommandLineRunner importDataFromReservations(MovieListingService movieListingServiceservice,
                                                        ReservationService reservationService){
        return args -> {

            reservationService.create(new Reservation("name", 1, true,
                    movieListingServiceservice.get(1).get()));

        };
    }
    */


}
