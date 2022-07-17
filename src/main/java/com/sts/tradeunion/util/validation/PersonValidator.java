package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.PersonDTO;
import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonEntity.class.equals(clazz);
    }

    //проверка адресов на соответствие паттерну
    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO person = (PersonDTO) target;

        if(!person.getBirthPlace().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("birthPlace","","Поле содержит недопустиные символы или не соответсвует шаблону");
        if(!person.getLivePlace().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("livePlace","","Поле содержит недопустиные символы или не соответсвует шаблону");
        if(!person.getRegPlace().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("regPlace","","Поле содержит недопустиные символы или не соответсвует шаблону");
        if(!person.getAddress().matches(getSomePersonsPlaceMatch()))
            errors.rejectValue("address","","Поле содержит недопустиные символы или не соответсвует шаблону");
    }
    private String getSomePersonsPlaceMatch(){
        String orEmptyStart = "(";
        // (г.Минск, ) (г.п.Витьба, ) (д.Щучино, )
        String town = "(([а-я])|([а-я]\\.[а-я]))\\.[а-яА-Я]{1,15},\\s?";
        // (ул.Мещерякова) (2 пер.Богратиона) (пр.Машерова) (ул.Розы Люксембург)
        String street = "(((ул)|(\\d{0,2}\\s?(пер)))|(пр))\\.((\\s?[а-яА-Я-]{1,15}){1,4})\\s?";
        // (, д.24) (-23) (,д. 45б) (- 45в)
        String houseNumber = "((,\\s?(д)\\.)|-)\\s?\\d{1,4}([а-я]?)\\.?";
        //(, кв. 132) (к. 31в) (-72) (,789)
        String quarterNumberIfExists = "(((,\\s?((кв|к)\\.)?)|-\\s?)\\s?\\d{1,4}([а-я]?))";
        String orEmptyEnd = ")|\\w{0}";
        return orEmptyStart + town + street + houseNumber + quarterNumberIfExists + orEmptyEnd;
    }
}
