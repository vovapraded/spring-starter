package by.javaguru.spring.database.repository;

import by.javaguru.spring.database.entity.User;
import by.javaguru.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

}
