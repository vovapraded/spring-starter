package by.javaguru.spring.config;

import by.javaguru.spring.database.repository.UserRepository;
import by.javaguru.spring.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("by.javaguru.spring")
public class ApplicationConfiguration {
}
