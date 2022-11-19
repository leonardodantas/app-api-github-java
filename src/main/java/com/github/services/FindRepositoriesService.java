package com.github.services;

import com.github.jsons.Root;
import com.github.operations.Filter;
import com.github.operations.Order;
import com.github.rest.FindRepositoriesServiceRest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FindRepositoriesService {

    private final FindRepositoriesServiceRest findRepositoriesServiceRest;

    public FindRepositoriesService(final FindRepositoriesServiceRest findRepositoriesServiceRest) {
        this.findRepositoriesServiceRest = findRepositoriesServiceRest;
    }

    public Collection<Root> execute(final String user, final Filter filter, final Order order, final String search) {
        log.info("Filter: {}", filter.getValue());
        log.info("Order: {}", order.getValue());
        final var root = findRepositoriesServiceRest.execute(user);
        final var rootFiltered = filter.execute(root);
        final var rootFilteredAndOrderly = order.execute(rootFiltered);

        return searchInList(search, rootFilteredAndOrderly);
    }

    private List<Root> searchInList(final String search, final List<Root> rootFilteredAndOrderly) {
        if (StringUtils.isBlank(search)) {
            return rootFilteredAndOrderly;
        }
        log.info("Search: {}", search);
        return rootFilteredAndOrderly.stream().filter(r -> r.name.toUpperCase(Locale.ROOT).contains(search.toUpperCase(Locale.ROOT))).collect(Collectors.toList());
    }

}
