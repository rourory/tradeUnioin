package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.PaymentDTO;
import com.sts.tradeunion.services.PersonService;
import com.sts.tradeunion.services.TradeUnionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PaymentValidator implements Validator {

    private final PersonService personService;
    private final TradeUnionService tradeUnionService;

    public PaymentValidator(PersonService personService, TradeUnionService tradeUnionService) {
        this.personService = personService;
        this.tradeUnionService = tradeUnionService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentDTO.class.equals(clazz);
    }


    /**
     * Проверка полей класса PaymentDTO на соответствие установленным шаблонам.
     *
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link PaymentDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {
        PaymentDTO payment = (PaymentDTO) target;

        if (personService.isExists(payment.getOwner().getId()))
            errors.rejectValue("owner", "", "Такого человека нет в базе");
        if (!tradeUnionService.findById(payment.getTradeUnionId()).isPresent())
            errors.rejectValue("tradeUnionId", "", "Такого профсоюза нет в базе");


    }
}
