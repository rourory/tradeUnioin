package com.sts.tradeunion.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Исключение, выбрасываемое при попытке получить информацию о несуществующем объекте
 * класса {@link com.sts.tradeunion.entities.PersonEntity} из базы данных
 */
@Data
@AllArgsConstructor
public class PersonNotFoundException extends RuntimeException{
    private int entityId;
}
