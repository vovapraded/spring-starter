package by.javaguru.spring.validator;

import by.javaguru.spring.dto.UserCreateEditDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;
@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo,UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext constraintValidatorContext) {
        return  hasText(value.getFirstname())||hasText(value.getLastname());
    }
}
