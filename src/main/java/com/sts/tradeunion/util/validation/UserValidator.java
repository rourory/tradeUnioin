package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.UserDTO;
import com.sts.tradeunion.services.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    /**
     * Проверка полей класса {@link com.sts.tradeunion.dto.UserDTO} на соответствие установленным шаблонам
     *
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link UserDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        if (userService.findByUsername(user.getUsername()).isPresent())
            errors.rejectValue("username","","Пользователь с таким именем уже существует");
    }
}
