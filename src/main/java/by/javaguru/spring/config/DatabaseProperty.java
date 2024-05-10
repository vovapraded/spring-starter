package by.javaguru.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
@ConfigurationProperties(prefix = "db")
public record DatabaseProperty(String username,
                               String password,
                               String driver,
                               String url,
                               String hosts,
                               PoolProperties pool,
                               List<PoolProperties> pools,
                               Map<String, Object> properties) {
    public static record PoolProperties(Integer timeout,Integer size){}
}
