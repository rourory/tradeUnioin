package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.PersonEntity;
import com.sts.tradeunion.entities.сlassification.TradeUnionClassification;
import lombok.Data;

import java.util.Date;

@Data
public class MembershipCardEntity {

    private int id;

    private int cardNumber;

    private Date completed;

    private Date finished;

    private PersonEntity owner;

    private TradeUnionClassification tradeUnion;
}
