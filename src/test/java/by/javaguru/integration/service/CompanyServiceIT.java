package by.javaguru.integration.service;

import by.javaguru.annotation.IT;
import by.javaguru.spring.ApplicationRunner;
import by.javaguru.spring.config.DatabaseProperty;
import by.javaguru.spring.dto.CompanyReadDto;
import by.javaguru.spring.listener.EntityEvent;
import by.javaguru.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//@ActiveProfiles("test")
//@SpringBootTest(classes = ApplicationRunner.class)
@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)

public class CompanyServiceIT {

    private  static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    private final DatabaseProperty databaseProperty;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);
        assertTrue(actualResult.isPresent());
        var expectResult = new CompanyReadDto(COMPANY_ID,null);
        actualResult.ifPresent(actual -> assertEquals(expectResult,actual ));
        System.out.println("sucsecc");
    }
}
