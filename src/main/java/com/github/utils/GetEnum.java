package com.github.utils;

import com.github.operations.Filter;
import com.github.operations.Order;

import java.util.Arrays;

public class GetEnum {

    public static Filter filter(final String filter) {
        return Arrays.stream(Filter.values()).filter(
                f -> f.getValue().equalsIgnoreCase(filter)
        ).findFirst().orElse(Filter.ACTIVE);
    }

    public static Order order(final String order) {
        return Arrays.stream(Order.values()).filter(
                f -> f.getValue().equalsIgnoreCase(order)
        ).findFirst().orElse(Order.ALPHABETICAL);
    }
}
