package by.javaguru.spring.mapper;

import by.javaguru.spring.database.entity.Company;
import by.javaguru.spring.dto.CompanyReadDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper <Company,CompanyReadDto>{

    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(object.getId(),
                object.getName());
    }
}
