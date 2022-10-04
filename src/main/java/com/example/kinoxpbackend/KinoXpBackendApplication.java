package com.example.kinoxpbackend;

import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class KinoXpBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(KinoXpBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KinoXpBackendApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner importData(EmployeeService service) {
        return (args) -> {

            service.create(new Employee("name", "role", "email", "phone",
                    "username", "password", "salt"));
            service.create(new Employee("name", "role", "email", "phone",
                    "username2", "password", "salt"));

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
    @Bean
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
