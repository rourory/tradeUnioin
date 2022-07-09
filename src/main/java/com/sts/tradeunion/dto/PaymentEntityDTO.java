package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.сlassification.TradeUnionClassificationEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentEntityDTO {

    private int id;

    private Date finished;

    private TradeUnionClassificationEntity tradeUnion;

    private PersonEntity owner;

}
