package com.clip.core.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {

    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;
    private final List<ValidationErrorDTO> errors;
    
    public ErrorResponseDTO(int status, String error, String message, String path, LocalDateTime timestamp) {
        this(status, error, message, path, timestamp, null);
    }
}

