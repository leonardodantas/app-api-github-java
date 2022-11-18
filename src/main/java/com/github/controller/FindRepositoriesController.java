package com.github.controller;

import com.github.models.Root;
import com.github.services.FindRepositoriesService;
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
    public Collection<Root> findAllRepositories(@PathVariable final String user, @RequestParam final String filter, @RequestParam final String order) {
        return findRepositoriesService.execute(user, filter, order);
    }


}
