package com.sts.tradeunion.dto;

import lombok.Data;

@Data
public class EducationClassificationDTO extends AbstractDTO {

    private int id;

    private String name;

    private String shortName;

    private int charName;
}
