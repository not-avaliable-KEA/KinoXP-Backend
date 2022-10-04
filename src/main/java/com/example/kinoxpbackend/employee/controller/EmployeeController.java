package com.example.kinoxpbackend.employee.controller;


import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.repositories.EmployeeRepository;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import com.example.kinoxpbackend.factory.DtoFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/*
@Author Sofia Abrahamsen + Lasse Bøgeskov-Jensen
 */
@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository repository;

    private final EmployeeService service;

    public EmployeeController(EmployeeRepository repository, EmployeeService service)
    {
        this.repository = repository;
        this.service = service;
    }

    //create employee @get og @post

    @GetMapping("/create")
    public String createEmployee(){
        return "createEmployee";
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Employee> item = service.get(id);
        return ResponseEntity.ok().body(DtoFactory.fromEmployee(item.get()));
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody Employee dto){
            Employee item = service.create(dto);
            return ResponseEntity.ok().body(DtoFactory.fromEmployee(item));
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return
    }*/


    //delete employee @get og @post
    @GetMapping("/delete")
    public String deleteEmployee(){
        return "deleteEmployee";
    }
    //update employee @get og @post
    @GetMapping("/update")
    public String updateEmployee(){
        return "updateEmployee";
    }
    //view employee @get og @post
    @GetMapping("/view")
    public String viewEmployees(){
        return "ViewEmployees";
    }

}
