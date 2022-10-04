package com.example.kinoxpbackend.employee.controller;


import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.repositories.EmployeeRepository;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import com.example.kinoxpbackend.factory.DtoFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
@Author Sofia Abrahamsen + Lasse Bøgeskov-Jensen
 */
@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeRepository repository, EmployeeService service)
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Employee> item = service.get(id);
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
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow(() -> new ResourceNotFoundException("Customer %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body("{deleted: " + delete + "}");
    }


    //update employee @get og @post
    @GetMapping("/update")
    public String updateEmployee(){
        return "updateEmployee";
    }

    //view employee @get og @post
    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> viewAllEmployees(){
        return ResponseEntity.ok().body(DtoFactory.fromEmployees(service.getAll()));
    }

}
