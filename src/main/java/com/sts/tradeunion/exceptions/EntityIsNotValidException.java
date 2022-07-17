package com.sts.tradeunion.exceptions;

import com.sts.tradeunion.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
@AllArgsConstructor
public class EntityIsNotValidException extends RuntimeException{
    private BindingResult bindingResult;
    private AbstractDTO abstractDTO;
}
