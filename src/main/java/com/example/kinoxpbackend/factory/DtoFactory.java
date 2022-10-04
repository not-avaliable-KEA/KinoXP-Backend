package com.example.kinoxpbackend.factory;

import com.example.kinoxpbackend.KinoXpBackendApplication;
import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory
{
    private static ModelMapper modelMapper = KinoXpBackendApplication.modelMapper();


    /*
        Employees
    */
    public static EmployeeDTO fromEmployee(Employee employee){
        return modelMapper.map(employee, EmployeeDTO.class);
    }
    public static Employee fromEmployeeDto(EmployeeDTO dto){
        return modelMapper.map(dto, Employee.class);
    }

    public static List<EmployeeDTO> fromEmployees(List<Employee> employees){
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }


















}
