package com.solarsmart.frontoffice.models.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private boolean isSuccess = false;
    private String message;
}
