package com.sts.tradeunion.util.validation.responce;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Представляет собой нарушение валидации сущности.
 * <p>
 * Содержит информацию о имени аттрибута сущности и списке нарушений валидации этого аттрибута.
 * @see EntityValidResponse
 */

@Data
@AllArgsConstructor
public class EntityValidViolation {
    private String attribute;
    private List<String> messages;
}
