package by.javaguru.spring;

import by.javaguru.spring.config.DatabaseProperty;
import by.javaguru.spring.database.repository.CompanyRepository;
import by.javaguru.spring.pool.ConnectionPool;
import by.javaguru.spring.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.SpringProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        context.getBean(CompanyRepository.class);
    }
}
