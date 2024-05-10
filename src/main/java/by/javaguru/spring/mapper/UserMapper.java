package by.javaguru.spring.mapper;

import by.javaguru.spring.dto.UserDto;
import by.javaguru.spring.database.entity.User;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ToString
@Component
public class UserMapper implements Mapper<User, UserDto> {

@Autowired()
private  UserDto userDto;
    @Override
    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .birthDate(user.getBirthDate())
                .company(user.getCompany())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername()).build();
    }
}
