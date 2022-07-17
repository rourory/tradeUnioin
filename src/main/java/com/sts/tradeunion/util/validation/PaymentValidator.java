package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.PaymentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PaymentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
