package com.github.controllers;

import com.github.jsons.Root;
import com.github.services.FindRepositoriesService;
import com.github.utils.GetEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("repositories")
public class FindRepositoriesController {

    private final FindRepositoriesService findRepositoriesService;

    public FindRepositoriesController(final FindRepositoriesService findRepositoriesService) {
        this.findRepositoriesService = findRepositoriesService;
    }

    @GetMapping("{user}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Root> findAllRepositories(@PathVariable final String user, @RequestParam(required = false) final String filter, @RequestParam(required = false) final String order) {
        final var filterOperation = GetEnum.filter(filter);
        final var orderOperation = GetEnum.order(order);
        return findRepositoriesService.execute(user, filterOperation, orderOperation);
    }


}
