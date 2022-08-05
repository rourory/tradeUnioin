package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.EducationClassificationEntity;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * DTO над {@link EducationClassificationEntity}.
 * Валидация осуществляется при помощи {@link javax.validation.constraints}
 */
@Data
public class EducationClassificationDTO extends AbstractDTO {

    private int id;

    @Size(min = 5, max = 25, message = "Поле должно содержать не менее 5 и не более 25 символов")
    private String name;

    @Size(min = 5, max = 25, message = "Поле должно содержать не менее 5 и не более 25 символов")
    private String shortName;

    @Size(min = 1,max = 5, message = "Поле должно содержать не менее 1 и не более 5 символов")
    private String charName;
}
