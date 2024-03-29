package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.MembershipCardEntity;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO над {@link MembershipCardEntity}.
 * Валидация осуществляется как при помощи {@link javax.validation.constraints}, так и при помощи
 * реализации интерфейса {@link org.springframework.validation.Validator}.
 * @see com.sts.tradeunion.util.validation.MembershipCardValidator
 */
@Data
public class MembershipCardDTO extends AbstractDTO{

    @NotNull(message = "Поле должно быть заполнено")
    private int cardNumber;

    @NotNull(message = "Поле должно быть заполнено")
    private Date completed;

    @Future(message = "Дата окончания действия карты не может быть в прошлом")
    private Date finished;

    @NotNull(message = "Поле должно быть заполнено")
    private PersonDTO owner;

    @NotNull(message = "Поле должно быть заполнено")
    private int tradeUnionId;
}
