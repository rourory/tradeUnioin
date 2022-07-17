package com.sts.tradeunion.util.validation;

import com.sts.tradeunion.dto.LaborContractDTO;
import com.sts.tradeunion.services.LaborContractService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LaborContractValidator implements Validator {
    private final LaborContractService laborContractService;

    public LaborContractValidator(LaborContractService laborContractService) {
        this.laborContractService = laborContractService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LaborContractDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
