package com.sts.tradeunion.util;

import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.util.validation.responce.EntityValidResponse;
import com.sts.tradeunion.util.validation.responce.EntityValidViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    private ResponseEntity<EntityValidResponse> personValidationException(EntityIsNotValidException validException){
        List<String> fieldNames = new ArrayList<>();
        List<EntityValidViolation> violations = new ArrayList<>();

        Arrays.stream(validException.getAbstractDTO().getClass().getDeclaredFields()).forEach(field -> fieldNames.add(field.getName()));

        for (String fieldName: fieldNames) {
            if (validException.getBindingResult().hasFieldErrors(fieldName)){
                List<String> fieldErrors = new ArrayList<>();
                validException.getBindingResult().getFieldErrors(fieldName).forEach(error -> fieldErrors.add(error.getDefaultMessage()));
                violations.add(new EntityValidViolation(fieldName, fieldErrors));
            }
        }
        return new ResponseEntity<>(new EntityValidResponse(violations), HttpStatus.BAD_REQUEST);

    }

}
