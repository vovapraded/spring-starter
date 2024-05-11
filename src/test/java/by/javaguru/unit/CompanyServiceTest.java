//package by.javaguru.unit;
//
//import by.javaguru.spring.database.entity.Company;
//import by.javaguru.spring.database.repository.CompanyRepository;
//import by.javaguru.spring.dto.CompanyReadDto;
//import by.javaguru.spring.listener.EntityEvent;
//import by.javaguru.spring.service.CompanyService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.context.ApplicationEventPublisher;
//
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class CompanyServiceTest {
//    private static final Integer COMPANY_ID = 1;
//    @Mock
//    private CompanyRepository companyRepository;
//    @Mock
//    private ApplicationEventPublisher eventPublisher;
//    @InjectMocks
//    private CompanyService companyService;
//    @Test
//    void findById(){
//        Mockito.doReturn(Optional.of(new
//                Company(COMPANY_ID)))
//                .when(companyRepository).findById(COMPANY_ID);
//        var actualResult = companyService.findById(COMPANY_ID);
//        assertTrue(actualResult.isPresent());
//        var expectResult = new CompanyReadDto(COMPANY_ID,null);
//        actualResult.ifPresent(actual -> assertEquals(expectResult,actual ));
//        verify(eventPublisher).publishEvent(any(EntityEvent.class));
//        System.out.println("dd");
//    }
//}
