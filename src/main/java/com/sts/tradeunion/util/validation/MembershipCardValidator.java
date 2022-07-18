package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.MembershipCardDTO;
import com.sts.tradeunion.dto.PaymentDTO;
import com.sts.tradeunion.services.MembershipCardService;
import com.sts.tradeunion.services.PersonService;
import com.sts.tradeunion.services.TradeUnionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MembershipCardValidator implements Validator {

    private final PersonService personService;
    private final MembershipCardService membershipCardService;
    private final TradeUnionService tradeUnionService;

    public MembershipCardValidator(PersonService personService, MembershipCardService membershipCardService, TradeUnionService tradeUnionService) {
        this.personService = personService;
        this.membershipCardService = membershipCardService;
        this.tradeUnionService = tradeUnionService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MembershipCardDTO.class.equals(clazz);
    }

    /**
     * Проверка полей класса PaymentDTO на соответствие установленным шаблонам
     *
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link MembershipCardDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {

        MembershipCardDTO membershipCard = (MembershipCardDTO) target;
        if (!personService.isExists(membershipCard.getOwner().getId()))
            errors.rejectValue("owner", "", "Такого человека нет в базе");
        if (membershipCardService.findByCardNumber(membershipCard.getCardNumber()).isPresent())
            errors.rejectValue("cardNumber", "", "Карта с таким номером уже существует");
        if (!tradeUnionService.findById(membershipCard.getTradeUnionId()).isPresent())
            errors.rejectValue("tradeUnionId", "", "Такого профсоюза нет в базе");

    }
}
