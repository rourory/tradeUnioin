package com.sts.tradeunion.util;

import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
import com.sts.tradeunion.exceptions.SuchUserHaveAlreadyExisted;
import com.sts.tradeunion.util.validation.responce.EntityValidResponse;
import com.sts.tradeunion.util.validation.responce.EntityValidViolation;
import org.hibernate.exception.JDBCConnectionException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.*;


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

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
    private ResponseEntity<EntityValidResponse> entityValidationException(EntityIsNotValidException validException) {
        List<String> fieldNames = new ArrayList<>();
        List<EntityValidViolation> violations = new ArrayList<>();

        Arrays.stream(validException.getAbstractDTO().getClass().getDeclaredFields()).forEach(field -> fieldNames.add(field.getName()));

        logger.warn("Ошибка валидации. Валидируемые поля: {}" , String.join(", ", fieldNames));
        for (String fieldName : fieldNames) {
            if (validException.getBindingResult().hasFieldErrors(fieldName)) {
                List<String> errorMessages = new ArrayList<>();
                validException.getBindingResult().getFieldErrors(fieldName).forEach(error -> errorMessages.add(error.getDefaultMessage()));
                violations.add(new EntityValidViolation(fieldName, errorMessages));
                logger.warn("Поле: {}. Введенное значение: {}. Ошибки: {}. ",
                        fieldName,
                        Objects.requireNonNull(validException.getBindingResult().getFieldErrors(fieldName).get(0).getRejectedValue()).toString(),
                        String.join(", ", errorMessages));
            }
        }
        return new ResponseEntity<>(new EntityValidResponse(violations), HttpStatus.NON_AUTHORITATIVE_INFORMATION);

    }

    /**
     * Метод обрабатывает ошибку, возникающую в случае отсутствия в базе данных объекта класса {@link com.sts.tradeunion.entities.PersonEntity}
     * с указанным Id.
     * @param exception ошибка, выбрасываемая в случает остутствия пользователя в базе данных
     * @return ответ в виде строки с ошибкой и указанием id искомого объекта
     */
    @ExceptionHandler
    private ResponseEntity<Map<String,String>> personNotFoundException(EntityNotFoundException exception) {
        logger.warn("Сущность с id = {} не найдена", exception.getEntityId());
        return new ResponseEntity<>(Map.of("message", "Пользователь с id = " + exception.getEntityId() + " не найден")
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<Map<String,String>> suchUserHaveAlreadyExisted(SuchUserHaveAlreadyExisted exception) {
        logger.warn("Пользователь с именем = {} уже существует", exception.getUsername());
        return new ResponseEntity<>(Map.of("message", "Пользователь с именем " + exception.getUsername() + " уже существует")
                , HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    private ResponseEntity<Map<String,String>> databaseConnectionError(Throwable exception) {
        Throwable rootCause = com.google.common.base.Throwables.getRootCause(exception);
        if (rootCause instanceof ConnectException) {
            logger.warn("Ошибка соединения с базой данных", exception);
            return new ResponseEntity<>(Map.of("message", "Ошибка соединения с базой данных (" +exception.getMessage()+ ")")
                    , HttpStatus.ALREADY_REPORTED);
        }
        logger.warn("Неизвестная ошибка", exception);
        return new ResponseEntity<>(Map.of("message", "Ошибка (" +exception.getMessage()+ ")")
                , HttpStatus.ALREADY_REPORTED);
    }
}
