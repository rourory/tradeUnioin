package com.sts.tradeunion.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PaymentDTO {

    private int id;

    private Date finished;

    private int tradeUnionId;

    private PersonDTO owner;

}