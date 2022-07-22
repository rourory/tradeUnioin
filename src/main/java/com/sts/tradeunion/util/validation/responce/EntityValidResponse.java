package com.sts.tradeunion.util.validation.responce;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Представляет собой список нарушений для каждого из аттрибутов сущности.
 * @see EntityValidViolation
 */
@Data
@AllArgsConstructor
public class EntityValidResponse {
    private List<EntityValidViolation> violations;
}
