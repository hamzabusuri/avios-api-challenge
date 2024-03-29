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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParentalControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }
    @Test
    public void checkParentalControlLevel_permissibleMovie_expectIsPermissible() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dynamoDb/permission/level/PG/movie/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"movieId\":\"1\",\"canWatch\":true}"));
    }

    @Test
    public void checkParentalControlLevel_impermissibleMovie_expectIsNotPermissible() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dynamoDb/permission/level/U/movie/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"movieId\":\"2\",\"canWatch\":false}"));
    }

    @Test
    public void checkParentalControlLevel_forceError_expectTechnicalFailure() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dynamoDb/permission/level/A/movie/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason("System error"));
    }

    @Test
    public void checkParentalControlLevel_nonExistentMovie_expectTitleNotFoundError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dynamoDb/permission/level/U/movie/6").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("The movie service could not find the given movie"));
    }
}