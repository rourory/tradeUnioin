package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.PaymentEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO над {@link PaymentEntity}.
 * Валидация осуществляется как при помощи {@link javax.validation.constraints}, так и при помощи
 * реализации интерфейса {@link org.springframework.validation.Validator}.
 * @see PaymentEntity
 */
@Data
public class PaymentDTO extends AbstractDTO{

    private int id;

    private Date finished;

    @NotNull(message = "Поле должно быть заполнено")
    private int tradeUnionId;

    @NotNull(message = "Поле должно быть заполнено")
    private PersonDTO owner;

}
