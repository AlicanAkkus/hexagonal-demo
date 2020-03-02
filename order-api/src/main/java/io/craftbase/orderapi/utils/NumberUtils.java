package io.craftbase.orderapi.utils;

import java.math.BigDecimal;

public class NumberUtils {

    public static BigDecimal roundToTwoDigits(BigDecimal number) {
        return number.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}