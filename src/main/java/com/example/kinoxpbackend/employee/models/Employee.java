package com.example.kinoxpbackend.employee.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEES")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String role;
    private String email;
    private String telephone;

    @Column(unique=true)
    private String username;
    private String password;
    private String salt;

    public Employee(String name, String role, String email, String telephone,String username, String password, String salt) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.telephone = telephone;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public Employee update(Employee employee) {
        this.name = employee.getName();
        this.role = employee.getRole();
        this.email = employee.getEmail();
        this.telephone = employee.getTelephone();
        this.username = employee.getUsername();
        return this;
    }
}
