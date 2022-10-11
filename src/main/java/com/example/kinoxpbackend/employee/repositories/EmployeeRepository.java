package com.example.kinoxpbackend.employee.repositories;

import com.example.kinoxpbackend.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> getSingleEntityByUsername(String userName);

}
