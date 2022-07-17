package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.MembershipCardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MembershipCardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MembershipCardDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
