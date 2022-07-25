package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.PersonDTO;
import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.services.EducationService;
import com.sts.tradeunion.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;
    private final EducationService educationService;

    public PersonValidator(PersonService personService, EducationService educationService) {
        this.personService = personService;
        this.educationService = educationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDTO.class.equals(clazz);
    }

    /**
     * Проверка полей класса PersonDTO на соответствие установленному шаблону.
     *
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link PersonDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO person = (PersonDTO) target;

        if (!person.getBirthPlace().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("birthPlace", "", "Поле содержит недопустиные символы или не соответсвует шаблону");
        if (!person.getLivePlace().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("livePlace", "", "Поле содержит недопустиные символы или не соответсвует шаблону");
        if (!person.getRegPlace().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("regPlace", "", "Поле содержит недопустиные символы или не соответсвует шаблону");
        if (!person.getAddress().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("address", "", "Поле содержит недопустиные символы или не соответсвует шаблону");
        if (!educationService.findByName(person.getEducation()).isPresent())
            errors.rejectValue("education", "", "Поле не соответствует установленному шаблону");
    }

    /**
     * Возвращает шаблон физического адреса в виде регулярного выражения.
     * Следующие варианты адреса являются валидными:
     * <p>
     * - г.Минск, ул.Мещерякова, д.13, кв.83 <br>
     * - г.п. Чисть, 2ой пер.Богратиона,13б-83 <br>
     * - дер.Красное, ул.Красной Звезды-83,72а <br>
     * - гор.Набережные Челны, ул.Розы Люксембург, д.34-1, кв.83 <br>
     * - г.Брест-Литовск, ул.50 лет Победы, д.34 <br>
     * - г.Минск, пр.Машерова,38-702б
     * @return Шаблон физического адреса в виде символьной строки.
     **/
    private String getSomePersonsPlaceMatch() {
        String orEmptyStart = "(";
        String town = "([а-я\\.]{1,4})\\.\\s?([а-яА-Я-]{1,15}\\s?){1,3},\\s?";
        String street = "(((ул)|(\\d{0,2}\\s?-?\\s?[оиыйая]{0,3}\\s?(пер)))|(пр))\\.(\\s?[\\dа-яА-Я-]{1,15}){1,4}\\s?";
        String houseNumber = "((,\\s?(д)\\.)|-|,)\\s?\\d{1,4}\\s?-?\\s?([а-я\\d]?)\\.?";
        String quarterNumberIfExists = "((((,\\s?((кв|к)\\.)?)|\\s?(-))\\s?\\d{1,4}([а-я]?))|\\w{0})";
        String orEmptyEnd = ")|\\w{0}";
        return orEmptyStart + town + street + houseNumber + quarterNumberIfExists + orEmptyEnd;
    }
}