package com.sts.tradeunion.exceptions;

import com.sts.tradeunion.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

/**
 * Исключение, выбрасываемое при возникновении ошибок валидации сущностей.
 * <p>
 * Хранит в себе информацию о валидируемом объекте сущности ({@code abstractDTO}) и ошибках ({@code bindingResult})
 */
@Data
@AllArgsConstructor
public class EntityIsNotValidException extends RuntimeException{
    private BindingResult bindingResult;
    private AbstractDTO abstractDTO;
}
