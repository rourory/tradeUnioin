package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.сlassification.TradeUnionClassificationEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MembershipCardDTO {

    private int id;

    private int cardNumber;

    private Date completed;

    private Date finished;

    private PersonDTO owner;

    private int tradeUnionId;
}