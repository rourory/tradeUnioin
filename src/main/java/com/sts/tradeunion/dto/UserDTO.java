package com.sts.tradeunion.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO extends AbstractDTO{

    private int id;

    @NotEmpty(message = "Поле должно быть не пустым")
    @Size(min = 4, max = 30, message = "Имя пользователя должно состоять из не менее 4 и не более 30 символов")
    private String username;

//    @NotEmpty(message = "Поле должно быть не пустым")
//    @Size(min = 4, max = 40, message = "Пароль должен состоять из не менее 4 и не более 40 символов")
//    private String password;

    @Size(min = 4, max = 15, message = "Роль должна состоять из не менее 4 и не более 15 символов")
    private String role;
}
