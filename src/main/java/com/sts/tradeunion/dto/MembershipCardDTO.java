package com.sts.tradeunion.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MembershipCardDTO extends AbstractDTO{

    private int id;

    private int cardNumber;

    private Date completed;

    private Date finished;

    private PersonDTO owner;

    private int tradeUnionId;
}
