package by.javaguru.spring.database.repository;

import by.javaguru.spring.database.entity.User;
import by.javaguru.spring.dto.QPredicates;
import by.javaguru.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static by.javaguru.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {
    private final EntityManager entityManager;
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
      var predicate = QPredicates.builder()
              .add(filter.firstname(),user.firstname::containsIgnoreCase)
              .add(filter.lastname(),user.lastname::containsIgnoreCase)
              .add(filter.birthDate(),user.birthDate::before)
              .build();
      return new JPAQuery<User>(entityManager)
              .select(user)
              .from(user)
              .where(predicate)
              .fetch();


    }
}
