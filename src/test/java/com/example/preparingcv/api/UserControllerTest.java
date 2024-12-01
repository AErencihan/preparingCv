package com.example.preparingcv.api;

import com.example.preparingcv.dto.request.UserRequest;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest extends BaseIntegrationTest {


    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }


    @Test
    void createUSer() throws Exception {
        UserRequest request = new UserRequest();
        request.setUserName("aa");
        request.setUserSurname("aa");
        request.setEmail("aaa");


        mvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString());


    }

    @Test
    void deleteUser() throws Exception {
        UserRequest request = new UserRequest();
        request.setUserName("aa");
        request.setUserSurname("aa");
        request.setEmail("aaa");

        mvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString());


        mvc.perform(MockMvcRequestBuilders.delete("/api/user/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        Optional<User> deletedUser = userRepository.findById(1L);
        assertTrue(deletedUser.isEmpty());

    }


    @Test
    void getUser() throws Exception {
        UserRequest request = new UserRequest();
        request.setUserName("aa");
        request.setUserSurname("aa");
        request.setEmail("aaa");

        mvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString());


        mvc.perform(MockMvcRequestBuilders.get("/api/user/get/aa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString());


    }

    @Test
    void updateUser() throws Exception {
        UserRequest request = new UserRequest();
        request.setUserName("aa");
        request.setUserSurname("aa");
        request.setEmail("aaa");

        mvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString());

        Optional<User> user = userRepository.findById(1L);
        User savedUser = user.get();
        savedUser.setUserName("sasssasdcs");

        mvc.perform(MockMvcRequestBuilders.put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sasssasdcs"))
                .andExpect(jsonPath("$.surName").isString())
                .andExpect(jsonPath("$.email").isString());


    }

}