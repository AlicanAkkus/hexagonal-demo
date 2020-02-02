package io.craftbase.restaurantapi.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantApiBusinessException extends RuntimeException {

    private String key;

    public RestaurantApiBusinessException(String key) {
        this.key = key;
    }
}