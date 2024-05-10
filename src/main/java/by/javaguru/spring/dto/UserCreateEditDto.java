package by.javaguru.spring.dto;

import by.javaguru.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    Integer companyId;
}
