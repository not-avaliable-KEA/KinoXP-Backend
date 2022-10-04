package com.example.kinoxpbackend.employee.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

        private Long id;

        private String name;
        private String role;
        private String email;
        private String telefon;

}
