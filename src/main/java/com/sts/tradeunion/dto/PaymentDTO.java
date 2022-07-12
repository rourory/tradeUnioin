package com.sts.tradeunion.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class PaymentDTO {

    private int id;

    private Date finished;

    private int tradeUnionId;

    private PersonDTO owner;

}
