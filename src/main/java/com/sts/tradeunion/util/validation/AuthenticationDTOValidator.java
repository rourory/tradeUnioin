package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.AuthenticationDTO;
import com.sts.tradeunion.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AuthenticationDTOValidator implements Validator {

    private final UserService userService;

    public AuthenticationDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthenticationDTO.class.equals(clazz);
    }

    /**
     * Проверка полей класса {@link com.sts.tradeunion.dto.AuthenticationDTO} на соответствие установленным шаблонам
     *
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link AuthenticationDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {
        AuthenticationDTO authenticationDTO = (AuthenticationDTO) target;

        if(userService.findByUsername(authenticationDTO.getUsername()).isPresent())
            errors.rejectValue("username","","Пользователь с таким именем уже существует");
    }
}
