package com.sts.tradeunion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO extends AbstractDTO{

    @NotEmpty(message = "Поле должно быть не пустым")
    @Size(min = 4, max = 30, message = "Имя пользователя должно состоять из не менее 4 и не более 30 символов")
    private String username;

    @Size(min = 4, max = 15, message = "Роль должна состоять из не менее 4 и не более 15 символов")
    private String role;

    @NotEmpty(message = "Поле должно быть не пустым")
    @Size(min = 4, max = 30, message = "Имя  должно состоять из не менее 4 и не более 30 символов")
    private String firstName;

    @NotEmpty(message = "Поле должно быть не пустым")
    @Size(min = 4, max = 30, message = "Фамилия должна состоять из не менее 4 и не более 30 символов")
    private String lastName;
}
