package com.example.kinoxpbackend;

import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.services.EmployeeService;
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

    @Bean
    public CommandLineRunner importData(EmployeeService service) {
        return (args) -> {
            service.create("name", "role", "email", "phone",
                    "username", "password");
            log.info("Created starting employee");
        };
    }
}
