package by.javaguru.spring.dto;

import by.javaguru.spring.database.entity.Company;
import by.javaguru.spring.database.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component

public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private Role role;
    private Company company;
}
