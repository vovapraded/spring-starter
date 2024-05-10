package by.javaguru.integration;

import by.javaguru.spring.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
@TestConfiguration
public class TestApplicationRunner {
    @SpyBean(name = "connectionPool")
    private ConnectionPool pool1;
}
