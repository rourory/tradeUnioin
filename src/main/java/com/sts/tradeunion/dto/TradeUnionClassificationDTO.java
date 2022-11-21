package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.TradeUnionClassificationEntity;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * DTO над {@link TradeUnionClassificationEntity}.
 * Валидация осуществляется при помощи {@link javax.validation.constraints}
 */
@Data
public class TradeUnionClassificationDTO extends AbstractDTO{

    @Size(min = 5, max = 30, message = "Поле должно содержать не менее 5 и не более 30 символов")
    private String name;

    @Size(min = 5, max = 30, message = "Поле должно содержать не менее 5 и не более 30 символов")
    private String city;

    @Size(min = 5, max = 30, message = "Поле должно содержать не менее 5 и не более 30 символов")
    private String address;


}
