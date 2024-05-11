package by.javaguru.spring.dto;

import by.javaguru.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
     Long id;
     String username;
     LocalDate birthDate;
     String firstname;
     String lastname;
     Role role;
     String image;
     CompanyReadDto company;
}
