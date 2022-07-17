package com.sts.tradeunion.util.validation.responce;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EntityValidResponse {
    private List<EntityValidViolation> violations;
}
