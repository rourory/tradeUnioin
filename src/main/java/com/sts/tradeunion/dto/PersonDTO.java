package com.sts.tradeunion.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class PersonDTO extends AbstractDTO {

    private int id;

    @NotBlank(message = "Поле должно быть не пустым")
    @Pattern(regexp = "(\\s?[а-яА-Я]{2,20}\\s?-?){1,3}", message = "Поле должно состоять из не менее двух и не более двадцати русских символов в одном слове")
    private String lastName;

    @NotBlank(message = "Поле должно быть не пустым")
    @Pattern(regexp = "[а-яА-Я]{2,20}", message = "Поле должно состоять из не менее двух и не более двадцати русских символов в одном слове")
    private String firstName;

    @Pattern(regexp = "([а-яА-Я-]{2,20})|(\\w{0})",message = "Поле должно состоять из не менее двух и не более двадцати русских символов в одном слове или быть пустым")
    private String middleName;

    @NotNull(message = "Поле даты рождения должно быть не пустым")
    @Past(message = "Дата рождения не должна быть в будущем")
    private Date birthDate;

    @Pattern(regexp = "([а-яА-Я-]{1,20})|\\w{0}" , message = "Поле должно состоять из не менее одного и не более двадцати русских символов")
    private String education;

    private String address;

    @Pattern(regexp = "\\+(375(17|29|33|44))-\\d{3}-\\d{2}-\\d{2}", message = "Мобильный телефон должен быть указан в международном формате (+375(код)-111-11-11)")
    private String phone;

    private String birthPlace;

    private String livePlace;

    private String regPlace;

    @NotNull(message = "Укажите семейное положение")
    private int maritalState;

    @Pattern(regexp = "([а-яА-Я-]{2,20})|(\\w{0})",message = "Поле должно состоять из не менее двух и не более двадцати русских символов в одном слове или быть пустым")
    private String citizenship;

    @Pattern(regexp = "([а-яА-Я-]{2,20})|(\\w{0})",message = "Поле должно состоять из не менее двух и не более двадцати русских символов в одном слове или быть пустым")
    private String nationality;

    @Pattern(regexp = "(\\.{1,255})|(\\w{0})",message = "Поле должно состоять из не менее 1 и не более 255 символов или быть пустым")
    private String comment;
}
