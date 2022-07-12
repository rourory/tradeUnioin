package com.sts.tradeunion.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LaborContractDTO {

    private int id;

    private String place;

    private String post;

    private Date finished;

    private PersonDTO owner;
}
