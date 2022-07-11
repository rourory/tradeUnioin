package com.sts.tradeunion.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentEntityDTO {

    private int id;

    private Date finished;

    private int tradeUnionId;

    private PersonDTO owner;

}