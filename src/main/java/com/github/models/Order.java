package com.github.models;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Order {

    ALPHABETICAL("alphabetical") {
        @Override
        public List<Root> order(final List<Root> root) {
            Collections.sort(root, Comparator.comparing(o -> o.name));
            return root;
        }
    }, LAST_COMMIT("lastCommit") {
        @Override
        public List<Root> order(final List<Root> root) {
            Collections.sort(root, new Comparator<Root>() {
                @Override
                public int compare(final Root o1, final Root o2) {
                    final var localDateO1 = ZonedDateTime.parse(o1.updated_at).toLocalDateTime();
                    final var localDateO2 = ZonedDateTime.parse(o2.updated_at).toLocalDateTime();
                    return localDateO2.compareTo(localDateO1);
                }
            });
            return root;
        }
    };

    Order(final String value) {

    }

    public abstract List<Root> order(final List<Root> root);
}
