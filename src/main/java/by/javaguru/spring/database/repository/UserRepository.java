package by.javaguru.spring.database.repository;

import by.javaguru.spring.database.entity.User;
import by.javaguru.spring.dto.IPersonalInfo;
import by.javaguru.spring.dto.PersonalInfo;
import by.javaguru.spring.dto.UserFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, FilterUserRepository,
        QuerydslPredicateExecutor<User> {
    Slice<User> findAllBy(Pageable pageable);
    List<User> findFirst3By(Sort sort);
    Optional<User> findFirstByCompanyIsNotNullOrderByIdDesc();
    @Query(
            "select u from User  u  where u.firstname like %:firstname%  and u.lastname like %:lastname%"
    )
    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname, String lastname);
    @Query(value = "select u.firstname,u.lastname,u.birth_date from  users u where company_id = :companyId" ,nativeQuery = true)
    List<IPersonalInfo> findAllByCompanyId(Integer companyId);

}