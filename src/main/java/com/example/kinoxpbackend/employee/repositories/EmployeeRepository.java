package com.example.kinoxpbackend.employee.repositories;

import com.example.kinoxpbackend.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getSingleEntityByUsername(String userName);

}
