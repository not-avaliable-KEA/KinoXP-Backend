package com.example.kinoxpbackend.employee.services;

import com.example.kinoxpbackend.employee.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @Test
    void create() {
        // arrange
        Employee employee = new Employee("name", "role", "email", "phone",
                "username", "password", "salt");

        // act
        Employee result = service.create(new Employee("name", "role", "email", "phone",
                "username", "password", "salt"));

        // assert
        // basics
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getRole(), result.getRole());
        assertEquals(employee.getEmail(), result.getEmail());
        assertEquals(employee.getTelephone(), result.getTelephone());
        assertEquals(employee.getUsername(), result.getUsername());
        // password generation
        assertNotEquals(employee.getPassword(), result.getPassword());
        assertEquals(64, result.getPassword().length());
        assertNotEquals(employee.getSalt(), result.getSalt());
        assertEquals(16, result.getSalt().length());

        service.delete(result.getId());
    }

    @Test
    void update_WithoutPassword() {
        // arrange
        Employee employee = service.create(new Employee("name", "role", "email", "phone",
                "username", "password", "salt"));

        Employee toUpdateTo = new Employee("new name", "new role", "new email", "new phone",
                "new username", "", "");
        toUpdateTo.setId(employee.getId());
        // act

        Employee result = service.update(toUpdateTo);

        // assert
        // check that toUpdateTo and result do not become the same object
        assertNotEquals(toUpdateTo, result);
        // basics
        assertEquals(toUpdateTo.getId(), result.getId());
        assertEquals(toUpdateTo.getName(), result.getName());
        assertEquals(toUpdateTo.getRole(), result.getRole());
        assertEquals(toUpdateTo.getEmail(), result.getEmail());
        assertEquals(toUpdateTo.getTelephone(), result.getTelephone());
        assertEquals(toUpdateTo.getUsername(), result.getUsername());
        // password check
        assertEquals(employee.getPassword(), result.getPassword());
        assertEquals(employee.getSalt(), result.getSalt());

        service.delete(result.getId());
    }

    @Test
    void update_WithPassword() {
        // arrange
        Employee employee = service.create(new Employee("name", "role", "email", "phone",
                "username", "password", "salt"));

        Employee toUpdateTo = new Employee("new name", "new role", "new email", "new phone",
                "new username", "new password", "");
        toUpdateTo.setId(employee.getId());
        // act

        Employee result = service.update(toUpdateTo);

        // assert
        // check that toUpdateTo and result do not become the same object
        assertNotEquals(toUpdateTo, result);
        // basics
        assertEquals(toUpdateTo.getId(), result.getId());
        assertEquals(toUpdateTo.getName(), result.getName());
        assertEquals(toUpdateTo.getRole(), result.getRole());
        assertEquals(toUpdateTo.getEmail(), result.getEmail());
        assertEquals(toUpdateTo.getTelephone(), result.getTelephone());
        assertEquals(toUpdateTo.getUsername(), result.getUsername());
        // password check
        assertNotEquals(employee.getPassword(), result.getPassword());
        assertEquals(64, result.getPassword().length());
        assertNotEquals(employee.getSalt(), result.getSalt());
        assertEquals(16, result.getSalt().length());

        service.delete(result.getId());
    }

    @Test
    void checkLogin_CorrectPassword() {
        // arrange
        String username = "username";
        String password = "password";

        Employee employee = new Employee(null, null, null, null,
                username, password, null);

        Employee createdEmployee = service.create(new Employee("name", "role", "email", "phone",
                username, password, ""));

        // act
        Employee result = service.checkLogin(employee);

        // assert
        assertNotNull(result);
        assertEquals(service.get(createdEmployee.getId()).get().getId(), result.getId());
        assertEquals(service.get(createdEmployee.getId()).get().getUsername(), result.getUsername());

        service.delete(result.getId());
    }

    @Test
    void checkLogin_NotCorrectPassword() {
        // arrange
        String username = "username";
        String password = "not the correct password";

        Employee employee = new Employee(null, null, null, null,
                username, password, null);

        Employee createdEmployee = service.create(new Employee("name", "role", "email", "phone",
                username, "password", ""));

        // act
        Employee result = service.checkLogin(employee);

        // assert
        assertNull(result);

        service.delete(createdEmployee.getId());
    }
}