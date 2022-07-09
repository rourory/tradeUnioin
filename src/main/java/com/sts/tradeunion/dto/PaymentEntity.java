package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.сlassification.TradeUnionClassification;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentEntity {

    private int id;

    private Date finished;

    private TradeUnionClassification tradeUnion;

    private PersonEntity owner;

}
