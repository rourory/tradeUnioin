package com.sts.tradeunion.util.validation.responce;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EntityValidViolation {
    private String attribute;
    private List<String> messages;
}
