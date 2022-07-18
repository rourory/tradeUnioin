package com.sts.tradeunion.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PaymentDTO extends AbstractDTO{

    private int id;

    private Date finished;

    @NotNull(message = "Поле должно быть заполнено")
    private int tradeUnionId;

    @NotNull(message = "Поле должно быть заполнено")
    private PersonDTO owner;

}
