package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.TradeUnionClassificationDTO;
import com.sts.tradeunion.services.MembershipCardServiceImpl;
import com.sts.tradeunion.services.PersonServiceImpl;
import com.sts.tradeunion.services.TradeUnionServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrganizationValidator implements Validator {
    private final PersonServiceImpl personService;
    private final MembershipCardServiceImpl membershipCardService;
    private final TradeUnionServiceImpl tradeUnionService;

    public OrganizationValidator(PersonServiceImpl personService, MembershipCardServiceImpl membershipCardService, TradeUnionServiceImpl tradeUnionService) {
        this.personService = personService;
        this.membershipCardService = membershipCardService;
        this.tradeUnionService = tradeUnionService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TradeUnionClassificationDTO.class.equals(clazz);
    }

    /**
     * Проверка полей класса TradeUnionClassificationDTO на соответствие установленным шаблонам
     *
     * @param target Является валидируемым объектом, требующим приведения к валидируемом типу ({@link TradeUnionClassificationDTO}).
     * @param errors Является объектом {@link org.springframework.validation.BindingResult}, в который складываются ошибки валидации.
     **/
    @Override
    public void validate(Object target, Errors errors) {

        TradeUnionClassificationDTO membershipCard = (TradeUnionClassificationDTO) target;


    }
}
