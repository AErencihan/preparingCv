package com.example.preparingcv.api;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.dto.request.SkillRequest;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.SkillsRepository;
import com.example.preparingcv.repository.UserRepository;
import com.example.preparingcv.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class SkillControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private SkillService skillService;


    @Test
    void createSkill() {
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        SkillRequest request = new SkillRequest(null, "java", savedUser.getId());
        SkillsDto result = skillService.createSkill(request);

        assertNotNull(result);
        assertEquals(request.getSkillName(), result.getSkillName());

        List<Skill> savedSkills = skillsRepository.findAll();
        assertEquals(1, savedSkills.size());
        Skill savedSkill = savedSkills.get(0);

        assertEquals(request.getSkillName(), savedSkill.getSkillName());
    }

    @Test
    void getSkill() {
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        Skill skill = new Skill();
        skill.setUser(savedUser);
        skill.setSkillName("java");

        Skill savedSkill = skillsRepository.save(skill);

        SkillsDto result = skillService.getSkill(savedSkill.getSkillsId());

        assertNotNull(result);
        assertEquals(savedSkill.getSkillName(), result.getSkillName());
    }

    @Test
    void updateSkill() {
        User user = new User();
        user.setUserName("aa");
        user.setUserSurname("aaa");
        user.setEmail("aa");

        User savedUser = userRepository.save(user);

        Skill skill = new Skill();
        skill.setUser(savedUser);
        skill.setSkillName("java");

        Skill savedSkill = skillsRepository.save(skill);

        SkillRequest request = new SkillRequest();
        request.setSkillsId(savedSkill.getSkillsId());
        request.setSkillName("spring");
        request.setUserId(savedUser.getId());

        SkillsDto result = skillService.updateSkill(request);

        assertNotNull(result);
        assertEquals(request.getSkillName(), result.getSkillName());

    }


}