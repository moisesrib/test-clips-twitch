package com.clip.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationErrorDTO {
    private final String field;
    private final String message;
    
    @Override
    public String toString() {
        return field + ": " + message;
    }
} 