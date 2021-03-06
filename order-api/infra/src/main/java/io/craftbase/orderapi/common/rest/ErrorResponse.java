package io.craftbase.orderapi.common.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String errorCode;
    private String errorDescription;
}