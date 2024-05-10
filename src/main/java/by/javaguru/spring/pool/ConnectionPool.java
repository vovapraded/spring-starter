package by.javaguru.spring.pool;


import jakarta.annotation.PostConstruct;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@ToString
@Slf4j
public class ConnectionPool {
    private String username;
    private String password;
    private String url;
    private Integer poolsize;
    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.password}") String password,
                          @Value("${db.url}")  String url,
                          @Value("${db.pool.size}") Integer poolsize) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.poolsize = poolsize;
    }
    @PostConstruct
    public void init(){
        log.info("init ConnectionPool");

    }

}
