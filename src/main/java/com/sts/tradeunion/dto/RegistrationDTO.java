package com.sts.tradeunion.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO extends AbstractDTO{

    @NotEmpty(message = "Поле должно быть не пустым")
    @Size(min = 4, max = 30, message = "Имя пользователя должно состоять из не менее 4 и не более 30 символов")
    private String username;

    @NotEmpty(message = "Поле должно быть не пустым")
    @Size(min = 4, max = 40, message = "Пароль должен состоять из не менее 4 и не более 40 символов")
    private String password;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4, max = 30, message = "Имя пользователя должно состоять из не менее 4 и не более 30 символов")
    private String firstName;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4, max = 30, message = "Имя пользователя должно состоять из не менее 4 и не более 30 символов")
    private String lastName;
}
