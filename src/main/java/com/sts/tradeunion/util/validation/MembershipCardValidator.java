package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.MembershipCardDTO;
import com.sts.tradeunion.services.MembershipCardServiceImpl;
import com.sts.tradeunion.services.PersonServiceImpl;
import com.sts.tradeunion.services.TradeUnionServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MembershipCardValidator implements Validator {

    private final PersonServiceImpl personService;
    private final MembershipCardServiceImpl membershipCardService;
    private final TradeUnionServiceImpl tradeUnionService;

    public MembershipCardValidator(PersonServiceImpl personService, MembershipCardServiceImpl membershipCardService, TradeUnionServiceImpl tradeUnionService) {
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
        if (personService.isExist(membershipCard.getOwner().getId()))
            errors.rejectValue("owner", "", "Такого человека нет в базе");
        if (membershipCardService.findByCardNumber(membershipCard.getCardNumber()).isPresent())
            errors.rejectValue("cardNumber", "", "Карта с таким номером уже существует");
        if (!tradeUnionService.findById(membershipCard.getTradeUnionId()).isPresent())
            errors.rejectValue("tradeUnionId", "", "Такого профсоюза нет в базе");

    }
}
