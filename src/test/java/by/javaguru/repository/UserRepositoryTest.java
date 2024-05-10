package by.javaguru.repository;

import by.javaguru.annotation.IT;
import by.javaguru.spring.database.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    @Test
    void check(){
        var users = userRepository.findAllByFirstnameContainingAndLastnameContaining("Ivan","Ivanov");
        assertNotNull(users);
        assertThat(users).hasSize(1);
    }
    @Test
    void checkProjections(){
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }
    @Test
    void check3(){
        var users = userRepository.findFirst3By(Sort.by("id").descending());
        assertTrue(!users.isEmpty());
        assertThat(users).hasSize(3);

    }
    @Test
    void checkPageable() {
        var pageable = PageRequest.of(1,2,Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);
//        while (slice.hasNext()){
//            slice
        }
    }


