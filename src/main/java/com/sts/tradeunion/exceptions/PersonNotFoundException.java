package com.sts.tradeunion.exceptions;

/**
 * Исключение, выбрасываемое при попытке получить информацию о несуществующем объекте
 * класса {@link com.sts.tradeunion.entities.PersonEntity} из базы данных
 */
public class PersonNotFoundException extends RuntimeException{
}
