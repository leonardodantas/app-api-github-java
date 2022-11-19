package com.github.services;

import com.github.jsons.Root;
import com.github.operations.Filter;
import com.github.operations.Order;
import com.github.rest.FindRepositoriesServiceRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class FindRepositoriesService {

    private final FindRepositoriesServiceRest findRepositoriesServiceRest;

    public FindRepositoriesService(final FindRepositoriesServiceRest findRepositoriesServiceRest) {
        this.findRepositoriesServiceRest = findRepositoriesServiceRest;
    }

    public Collection<Root> execute(final String user, final Filter filter, final Order order) {
        log.info("Filter: {}", filter.getValue());
        log.info("Order: {}", order.getValue());
        final var root = findRepositoriesServiceRest.execute(user);
        final var rootFiltered = filter.execute(root);
        return order.execute(rootFiltered);
    }

}
