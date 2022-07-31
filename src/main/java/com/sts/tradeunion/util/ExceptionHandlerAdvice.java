package com.sts.tradeunion.util;

import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.PersonNotFoundException;
import com.sts.tradeunion.util.validation.responce.EntityValidResponse;
import com.sts.tradeunion.util.validation.responce.EntityValidViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Класс является exception handler для классов приложения,помеченных
 * аннотицией {@link org.springframework.web.bind.annotation.RestController}.
 * <p>
 * Методы класса, аннотированные {@link ExceptionHandler} и принимающие в
 * качестве аргумента конкретное исключение, являются метода, обрабатывающими
 * соответствующие исключения.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * Метод обрабатывает каждое поле валидируемого объекта на предмет наличия в объекте {@link org.springframework.validation.BindingResult} ошибок для поля,
     * соответсвующего имени поля валидируемого объекта
     * <p>
     *
     * @param validException объект исключения, хранящего в себе инофрмацию о валидируемом объекте,
     *                       наследнике абстрактного класса({@link com.sts.tradeunion.dto.AbstractDTO}) и об объекте
     *                       класса {@link org.springframework.validation.BindingResult}, содержащего, в свою очередь,
     *                       информацию о ошибках валидации в
     * @return Объект-обёртку {@link ResponseEntity<EntityValidResponse>} над классом {@link EntityValidResponse}, содержащий в себе
     * информацию о имени атрибута с ошибкой и списке самих ошибок
     * @see EntityValidResponse
     */
    @ExceptionHandler
    private ResponseEntity<EntityValidResponse> personValidationException(EntityIsNotValidException validException) {
        List<String> fieldNames = new ArrayList<>();
        List<EntityValidViolation> violations = new ArrayList<>();

        Arrays.stream(validException.getAbstractDTO().getClass().getDeclaredFields()).forEach(field -> fieldNames.add(field.getName()));

        for (String fieldName : fieldNames) {
            if (validException.getBindingResult().hasFieldErrors(fieldName)) {
                List<String> fieldErrors = new ArrayList<>();
                validException.getBindingResult().getFieldErrors(fieldName).forEach(error -> fieldErrors.add(error.getDefaultMessage()));
                violations.add(new EntityValidViolation(fieldName, fieldErrors));
            }
        }
        return new ResponseEntity<>(new EntityValidResponse(violations), HttpStatus.BAD_REQUEST);

    }

    /**
     * Метод обрабатывает ошибку, возникающую в случае отсутствия в базе данных объекта класса {@link com.sts.tradeunion.entities.PersonEntity}
     * с указанным Id.
     * @param notFoundException ошибка, выбрасываемая в случает остутствия пользователя в базе данных
     * @return ответ в виде строки с ошибкой и указанием id искомого объекта
     */
    @ExceptionHandler
    private ResponseEntity<String> personNotFoundException(PersonNotFoundException notFoundException) {
        return new ResponseEntity<>("Пользователь с id = " + notFoundException.getEntityId() + " не найден"
                , HttpStatus.NOT_FOUND);
    }

}
