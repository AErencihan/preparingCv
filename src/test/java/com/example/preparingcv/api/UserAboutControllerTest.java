package com.example.preparingcv.api;

import com.example.preparingcv.dto.UserDto;
import com.example.preparingcv.dto.request.UserAboutRequest;
import com.example.preparingcv.dto.request.UserRequest;
import com.example.preparingcv.model.User;
import com.example.preparingcv.model.UserAbout;
import com.example.preparingcv.repository.UserAboutRepository;
import com.example.preparingcv.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserAboutControllerTest extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAboutRepository userAboutRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userAboutRepository.deleteAll();
    }


    @Test
    void createUserAbout() throws Exception {
        UserRequest request = new UserRequest();
        request.setUserName("aa");
        request.setUserSurname("aa");
        request.setEmail("aaa");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getUserName()))
                .andExpect(jsonPath("$.surName").value(request.getUserSurname()))
                .andExpect(jsonPath("$.email").value(request.getEmail()))
                .andReturn();


        String responseBody = result.getResponse().getContentAsString();
        UserDto response = mapper.readValue(responseBody, UserDto.class);

        UserAboutRequest aboutRequest = new UserAboutRequest(null, "01.01.2000", "05555555555",
                "Istanbul", response.getId());


        mvc.perform(MockMvcRequestBuilders.post("/api/userAbout/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(aboutRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.birthDay").value(aboutRequest.getBirthDay()))
                .andExpect(jsonPath("$.phoneNumber").value(aboutRequest.getPhoneNumber()))
                .andExpect(jsonPath("$.address").value(aboutRequest.getAddress()));

    }

    @Test
    void getUserAbout() throws Exception {
        Long userId = createUser();

        UserAboutRequest aboutRequest = new UserAboutRequest(null, "01.01.2000", "05555555555",
                "Istanbul", userId);

        mvc.perform(MockMvcRequestBuilders.post("/api/userAbout/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(aboutRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.birthDay").value(aboutRequest.getBirthDay()))
                .andExpect(jsonPath("$.phoneNumber").value(aboutRequest.getPhoneNumber()))
                .andExpect(jsonPath("$.address").value(aboutRequest.getAddress()));


        mvc.perform(MockMvcRequestBuilders.get("/api/userAbout/get/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(aboutRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDay").value(aboutRequest.getBirthDay()))
                .andExpect(jsonPath("$.phoneNumber").value(aboutRequest.getPhoneNumber()))
                .andExpect(jsonPath("$.address").value(aboutRequest.getAddress()));

    }

    @Test
    void deleteUserAbout() throws Exception {
        Long userId = createUser();
        UserAboutRequest aboutRequest = new UserAboutRequest(null, "01.01.2000", "05555555555",
                "Istanbul", userId);

        mvc.perform(MockMvcRequestBuilders.post("/api/userAbout/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(aboutRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.birthDay").value(aboutRequest.getBirthDay()))
                .andExpect(jsonPath("$.phoneNumber").value(aboutRequest.getPhoneNumber()))
                .andExpect(jsonPath("$.address").value(aboutRequest.getAddress()));


        mvc.perform(MockMvcRequestBuilders.delete("/api/userAbout/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userId)))
                .andExpect(status().isOk());

        Optional<UserAbout> deletedUser = userAboutRepository.findById(userId);
        assertTrue(deletedUser.isEmpty());

    }


}