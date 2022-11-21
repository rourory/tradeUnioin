package com.sts.tradeunion.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Исключение, выбрасываемое при попытке получить информацию о несуществующем объекте - наследнике
 * класса {@link com.sts.tradeunion.entities.AbstractEntity} из базы данных
 */
@Data
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    private int entityId;
}
