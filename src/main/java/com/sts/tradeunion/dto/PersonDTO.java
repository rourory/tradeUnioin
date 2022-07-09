package com.sts.tradeunion.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PersonDTO {
    private int id;

    private String lastName;

    private String firstName;

    private String middleName;

    private Date birthDate;

    private String address;

    private String phone;

    private String birthPlace;

    private String livePlace;

    private String regPlace;

    private int maritalState;

    private String citizenship;

    private String nationality;

    private String comment;
}
