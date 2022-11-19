package com.github.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.jsons.Root;
import com.github.services.FindRepositoriesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import utils.FileRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FindRepositoriesControllerTest {

    @InjectMocks
    private FindRepositoriesController findRepositoriesController;

    @Mock
    private FindRepositoriesService findRepositoriesService;

    private MockMvc mockMvc;

    private final FileRepository fileRepository = new FileRepository();

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(findRepositoriesController).build();
    }

    @Test
    public void shouldExecuteSuccessWithoutParams() throws Exception {
        final var response = fileRepository.getContent("root-list", new TypeReference<List<Root>>() {
        });

        mockMvc.perform(MockMvcRequestBuilders.get("/repositories/leonardodantas")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldExecuteSuccessWithValidParams() throws Exception {
        final var response = fileRepository.getContent("root-list", new TypeReference<List<Root>>() {
        });

        when(findRepositoriesService.execute(anyString(), any(), any(), anyString()))
                .thenReturn(response);

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("filter", "active");
        params.add("order", "alphabetical");
        params.add("search", "github");

        mockMvc.perform(MockMvcRequestBuilders.get("/repositories/leonardodantas")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldExecuteSuccessWithInvalidParams() throws Exception {
        final var response = fileRepository.getContent("root-list", new TypeReference<List<Root>>() {
        });

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("filter", "disable");
        params.add("order", "true");

        mockMvc.perform(MockMvcRequestBuilders.get("/repositories/leonardodantas")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
