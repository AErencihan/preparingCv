package com.example.preparingcv.api;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.dto.request.SkillRequest;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.SkillsRepository;
import com.example.preparingcv.repository.UserRepository;
import com.example.preparingcv.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;


import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class SkillControllerTest extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillsRepository skillsRepository;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        skillsRepository.deleteAll();
    }


    @Test
    void createSkill() throws Exception {
        Long userId = createUser();

        SkillRequest skillRequest = new SkillRequest(null, "java", userId);

        mvc.perform(MockMvcRequestBuilders.post("/api/skill/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(skillRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.skillName").value(skillRequest.getSkillName()))
                .andReturn();


    }

    @Test
    void getSkill() throws Exception {
        Long userId = createUser();

        SkillRequest skillRequest = new SkillRequest(null, "java", userId);

        mvc.perform(MockMvcRequestBuilders.post("/api/skill/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(skillRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.skillName").value(skillRequest.getSkillName()))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders.get("/api/skill/get/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(skillRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.skillName").value(skillRequest.getSkillName()))
                .andReturn();


    }

    @Test
    void updateSkill() throws Exception {
        Long userId = createUser();

        SkillRequest skillRequest = new SkillRequest(null, "java", userId);

        mvc.perform(MockMvcRequestBuilders.post("/api/skill/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(skillRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.skillName").value(skillRequest.getSkillName()))
                .andReturn();

        Optional<Skill> skill = skillsRepository.findById(userId);
        Skill savedSkill = skill.get();

        SkillRequest skillRequest1 = new SkillRequest(savedSkill.getSkillsId(), "spring", userId);


        mvc.perform(MockMvcRequestBuilders.put("/api/skill/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(skillRequest1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.skillName").value("spring"))
                .andReturn();


    }


}