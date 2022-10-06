package com.example.kinoxpbackend.employee.controllers;


import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import com.example.kinoxpbackend.factory.DtoFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
@Author Sofia Abrahamsen + Lasse Bøgeskov-Jensen + Mathias Eliot Nielsen
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service)
    {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll(){
        return ResponseEntity.ok().body(DtoFactory.fromEmployees(service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Employee> item = service.get(id);

        if (item.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return ResponseEntity.ok().body(DtoFactory.fromEmployee(item.get()));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody Employee employee){
            Employee item = service.create(employee);
            return ResponseEntity.ok().body(DtoFactory.fromEmployee(item));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody Employee employee, @PathVariable("id") Long id){
        employee.setId(id);
        Employee item = service.update(employee);
        return ResponseEntity.ok().body(DtoFactory.fromEmployee(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
