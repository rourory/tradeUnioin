package com.sts.tradeunion.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO extends AbstractDTO{

    @NotEmpty(message = "Поле должно быть не пустым")
    @Pattern(regexp = "[A-Za-z\\d]{4,20}", message = "Поле должно состоять из букв латинского алфавита и(или) цифр")
    @Size(min = 4, max = 20, message = "Имя пользователя должно состоять из не менее 4 и не более 20 символов")
    private String username;

    @NotEmpty(message = "Поле должно быть не пустым")
    @Pattern(regexp = "[A-Za-z\\d]{4,30}", message = "Поле должно состоять из букв латинского алфавита и(или) цифр")
    @Size(min = 4, max = 30, message = "Пароль должен состоять из не менее 4 и не более 30 символов")
    private String password;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "([А-Щ]|[Э-Я])[а-я]{2,15}", message = "Имя Должно состоять из букв русского алфавита и не начинаться с букв Ь,Ы,Ъ")
    @Size(min = 2, max = 15, message = "Имя пользователя должно состоять из не менее 2 и не более 15 символов")
    private String firstName;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "([А-Щ]|[Э-Я])[а-я]{2,15}", message = "Фамилия должа состоять из букв русского алфавита и не начинаться с букв Ь,Ы,Ъ")
    @Size(min = 3, max = 30, message = "Имя пользователя должно состоять из не менее 4 и не более 30 символов")
    private String lastName;
}
