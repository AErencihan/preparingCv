package com.example.preparingcv.api;

import com.example.preparingcv.dto.request.ExperienceRequest;
import com.example.preparingcv.model.Experience;
import com.example.preparingcv.repository.ExperienceRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExperienceControllerTest extends BaseIntegrationTest {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        experienceRepository.deleteAll();
    }

    @Test
    void createExperience() throws Exception {
        Long userId = createUser();

        ExperienceRequest request = new ExperienceRequest("apple", "developer", "01.01.2000",
                "01.01.2030", userId, null);

        mvc.perform(MockMvcRequestBuilders.post("/api/experience/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value(request.getCompanyName()))
                .andExpect(jsonPath("$.position").value(request.getPosition()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate()))
                .andReturn();
    }

    @Test
    void updateExperience() throws Exception {
        Long userId = createUser();

        ExperienceRequest request = new ExperienceRequest("apple", "developer", "01.01.2000",
                "01.01.2030", userId, null);

        mvc.perform(MockMvcRequestBuilders.post("/api/experience/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value(request.getCompanyName()))
                .andExpect(jsonPath("$.position").value(request.getPosition()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate()))
                .andReturn();

        Optional<Experience> experience = experienceRepository.findById(userId);
        Experience savedExperience = experience.get();


        ExperienceRequest request1 = new ExperienceRequest("microsoft", "developer", "01.01.2000",
                "01.01.2030", userId, savedExperience.getExperienceId());


        mvc.perform(MockMvcRequestBuilders.put("/api/experience/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value(request1.getCompanyName()))
                .andReturn();

    }

    @Test
    void deleteExperience() throws Exception {
        Long userId = createUser();

        ExperienceRequest request = new ExperienceRequest("apple", "developer", "01.01.2000",
                "01.01.2030", userId, null);

        mvc.perform(MockMvcRequestBuilders.post("/api/experience/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value(request.getCompanyName()))
                .andExpect(jsonPath("$.position").value(request.getPosition()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate()))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders.delete("/api/experience/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userId)))
                .andExpect(status().isOk())
                .andReturn();

        Optional<Experience> deletedExperience = experienceRepository.findById(request.getUserId());
        assertTrue(deletedExperience.isEmpty());
    }

    @Test
    void getExperience() throws Exception {
        Long userId = createUser();

        ExperienceRequest request = new ExperienceRequest("apple", "developer", "01.01.2000",
                "01.01.2030", userId, null);

        mvc.perform(MockMvcRequestBuilders.post("/api/experience/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value(request.getCompanyName()))
                .andExpect(jsonPath("$.position").value(request.getPosition()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate()))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders.get("/api/experience/get/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value(request.getCompanyName()))
                .andExpect(jsonPath("$.position").value(request.getPosition()))
                .andExpect(jsonPath("$.startDate").value(request.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(request.getEndDate()))
                .andReturn();


    }


}