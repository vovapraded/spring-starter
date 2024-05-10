package by.javaguru.repository;

import by.javaguru.annotation.IT;
import by.javaguru.spring.database.entity.Company;
import by.javaguru.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@Transactional
public class CompanyRepositoryTest {
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;
    private final Integer APPLE_ID=4;
    @Test
    void findById(){
        var company = entityManager.find(Company.class,1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
        System.out.println(company.getLocales());
    }
    @Test
    void save(){
        var company = Company.builder()
                .name("dfd")
                .locales(Map.of("ru","Описание",
                        "en", "Description"))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
    @Test
    void delete(){
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty()
        );
    }
    @Test
    void checkFindByQueries(){
        var company=companyRepository.findByName("Google");
        System.out.println(company);
//        var companies = companyRepository.findAllByNameContainingIgnoreCase("a");
//        assertThat(companies).hasSize(3);
    }

}
