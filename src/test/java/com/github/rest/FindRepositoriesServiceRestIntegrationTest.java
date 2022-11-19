package com.github.rest;

import com.github.Application;
import com.github.exceptions.RestTemplateErrorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
public class FindRepositoriesServiceRestIntegrationTest {

    @Autowired
    private FindRepositoriesServiceRest findRepositoriesServiceRest;

    @Test
    public void shouldExecuteSuccess() {
        final var user = "leonardodantas";

        final var result = findRepositoriesServiceRest.execute(user);

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @Test(expected = RestTemplateErrorException.class)
    public void shouldRestTemplateErrorException() {
        final var user = "xs1s288sss";
        findRepositoriesServiceRest.execute(user);
    }
}
