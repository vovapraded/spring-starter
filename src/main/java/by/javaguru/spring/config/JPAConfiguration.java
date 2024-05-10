package by.javaguru.spring.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
@Conditional(JPACondition.class)
@Configuration
public class JPAConfiguration {
    @PostConstruct
    void init(){
        System.out.println("JPAConfiguration enabled");
    }
}
