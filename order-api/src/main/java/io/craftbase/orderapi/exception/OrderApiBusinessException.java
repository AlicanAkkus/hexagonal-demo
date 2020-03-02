package io.craftbase.orderapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderApiBusinessException extends RuntimeException {

    private String key;

    public OrderApiBusinessException(String key) {
        this.key = key;
    }
}