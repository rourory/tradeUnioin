package com.sts.tradeunion.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sts.tradeunion.entities.PersonEntity;

import javax.persistence.*;
import java.util.Date;

public class LaborContractDTO {

    private int id;

    private String place;

    private String post;

    private Date finished;

    private PersonDTO owner;
}
