package by.javaguru.spring.service;

import by.javaguru.spring.database.repository.CompanyRepository;
import by.javaguru.spring.dto.CompanyReadDto;
import by.javaguru.spring.listener.AccessType;
import by.javaguru.spring.listener.EntityEvent;
import by.javaguru.spring.mapper.CompanyReadMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyReadMapper companyReadMapper;

    private final ApplicationEventPublisher applicationEventPublisher;


    public Optional<CompanyReadDto> findById(Integer id){
        return  companyRepository.findById(id).map(entity ->{
            applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.getId(),null);
        });

    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }
}
