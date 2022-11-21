package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.PaymentDTO;
import com.sts.tradeunion.services.PersonServiceImpl;
import com.sts.tradeunion.services.TradeUnionServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PaymentValidator implements Validator {

    private final PersonServiceImpl personService;
    private final TradeUnionServiceImpl tradeUnionService;

    public PaymentValidator(PersonServiceImpl personService, TradeUnionServiceImpl tradeUnionService) {
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
        if (!personService.findById(payment.getOwner().getId()).isPresent())
            errors.rejectValue("owner", "", "Такого человека нет в базе");
        if (!tradeUnionService.findById(payment.getTradeUnionId()).isPresent())
            errors.rejectValue("tradeUnionId", "", "Такой организации нет в базе");


    }
}
