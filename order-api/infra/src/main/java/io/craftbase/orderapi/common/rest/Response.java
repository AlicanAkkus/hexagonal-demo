package io.craftbase.orderapi.common.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {

    private T data;
    private ErrorResponse errors;
}