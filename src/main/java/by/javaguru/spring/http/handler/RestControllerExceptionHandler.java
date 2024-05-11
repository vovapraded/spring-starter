package by.javaguru.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice(basePackages = "by.javaguru.spring.http.rest")

public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
