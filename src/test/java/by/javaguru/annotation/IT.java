package by.javaguru.annotation;

import by.javaguru.integration.TestApplicationRunner;
import by.javaguru.spring.ApplicationRunner;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@Transactional
@SpringBootTest(classes = {ApplicationRunner.class, TestApplicationRunner.class})
public @interface IT {
}
