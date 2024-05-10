package by.javaguru.integration.service;

import by.javaguru.annotation.IT;
import by.javaguru.spring.database.entity.Role;
import by.javaguru.spring.database.entity.User;
import by.javaguru.spring.database.repository.UserRepository;
import by.javaguru.spring.dto.UserCreateEditDto;
import by.javaguru.spring.dto.UserDto;
import by.javaguru.spring.dto.UserReadDto;
import by.javaguru.spring.mapper.UserMapper;
import by.javaguru.spring.pool.ConnectionPool;
import by.javaguru.spring.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor

public class UserServiceIT {
    private  static final Long USER_1 = 1L;
    private  static final Integer COMPANY_1 = 1;

    private final UserService userService;
    @MockBean
    private ConnectionPool pool1;
    @Test
    void findAll(){
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);

    }
    @Test
    void findById(){
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com",user.getUsername()));

    }
    @Test
    void create(){
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                COMPANY_1
        );
        UserReadDto actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(),actualResult.getUsername());
        assertEquals(userDto.getBirthDate(),actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(),actualResult.getFirstname());
        assertEquals(userDto.getLastname(),actualResult.getLastname());
        assertEquals(userDto.getCompanyId(),actualResult.getCompany().id());

        assertSame(userDto.getRole(),actualResult.getRole());



    }
    @Test
    void update(){
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                COMPANY_1
        );
       Optional<UserReadDto> actualResult = userService.update(USER_1,userDto);
       assertTrue(actualResult.isPresent());
       actualResult.ifPresent(user ->{
        assertEquals(userDto.getUsername(),user.getUsername());
        assertEquals(userDto.getBirthDate(),user.getBirthDate());
        assertEquals(userDto.getFirstname(),user.getFirstname());
        assertEquals(userDto.getLastname(),user.getLastname());
        assertEquals(userDto.getCompanyId(),user.getCompany().id());

        assertSame(userDto.getRole(),user.getRole());
    });



    }
    @Test
    void delete(){
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_1));
    }

}
