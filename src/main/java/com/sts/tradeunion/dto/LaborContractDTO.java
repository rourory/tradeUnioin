package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.LaborContractEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * DTO над {@link LaborContractEntity}.
 * Валидация осуществляется как при помощи {@link javax.validation.constraints}, так и при помощи
 * реализации интерфейса {@link org.springframework.validation.Validator}.
 * @see com.sts.tradeunion.util.validation.LaborContractValidator
 */
@Data
public class LaborContractDTO extends AbstractDTO {

    private int id;

    @NotBlank(message = "Поле должно быть заполнено")
    @Pattern(regexp = ".{3,50}", message = "Поле должно состоять из 3-50 символов")
    private String place;

    @NotBlank(message = "Поле должно быть заполнено")
    @Pattern(regexp = ".{3,50}", message = "Поле должно состоять из 3-50 символов")
    private String post;

    private Date finished;

    @NotNull(message = "Поле должно быть заполнено")
    private PersonDTO owner;
}
