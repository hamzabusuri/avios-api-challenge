package com.service.parentalcontrol.hamza;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParentalControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void isMoviePermissible_permissibleMovie_expectIsPermissible() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/permission/level/U/movie/alpha").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("true")));
    }

    @Test
    public void isMoviePermissible_impermissibleMovie_expectIsNotPermissible() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/permission/level/U/movie/echo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("false")));
    }

    @Test
    public void isMoviePermissible_forceError_expectTechnicalFailure() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/permission/level/U/movie/foxtrot").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason("There has been a system error"));
    }

    @Test
    public void isMoviePermissible_nonExistentMovie_expectTitleNotFoundError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/permission/level/U/movie/idontexist").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("The movie service could not find the given movie"));
    }
}