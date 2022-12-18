package com.sts.tradeunion.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Исключение, выбрасываемое при попытке зарегестрировать пользователя с уже занятим именем пользователя
 * класса {@link com.sts.tradeunion.entities.AbstractEntity} из базы данных
 */
@Data
@AllArgsConstructor
public class SuchUserHaveAlreadyExisted extends RuntimeException{
    private String username;
}
