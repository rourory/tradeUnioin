package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.LaborContractDTO;
import com.sts.tradeunion.services.PersonServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LaborContractValidator implements Validator {
    private final PersonServiceImpl personService;
    public LaborContractValidator( PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LaborContractDTO.class.equals(clazz);
    }

    /**
     * Проверка полей класса LaborContractDTO на соответствие установленным шаблонам
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link LaborContractDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {
        LaborContractDTO laborContract = (LaborContractDTO) target;

        if (personService.isExist(laborContract.getOwner().getId()))
            errors.rejectValue("owner", "", "Такого челвека нет в базе");
    }
}
