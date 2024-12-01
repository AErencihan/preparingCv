package com.example.preparingcv.api;


import com.example.preparingcv.dto.UserDto;
import com.example.preparingcv.dto.request.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("integration")
@AutoConfigureMockMvc
@SpringBootTest
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;


    public Long createUser() throws Exception {
        UserRequest request = new UserRequest();
        request.setUserName("aa");
        request.setUserSurname("aa");
        request.setEmail("aaa");


        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        UserDto response = mapper.readValue(responseBody, UserDto.class);

        return response.getId();

    }

}
