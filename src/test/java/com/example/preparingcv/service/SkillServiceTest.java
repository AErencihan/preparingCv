package com.example.preparingcv.service;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.dto.request.SkillRequest;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.SkillsRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkillServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final SkillsRepository skillsRepository = mock(SkillsRepository.class);
    private final SkillService skillService = new SkillService(skillsRepository, userRepository);


    @Test
    void createSkill(){
        Long userId = 1L;
        User user = new User();
        user.setId(1L);

        SkillRequest request = new SkillRequest(null, "java", userId);

        Skill skill = new Skill("java", user);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(skillsRepository.save(any(Skill.class))).thenReturn(skill);

        SkillsDto result = skillService.createSkill(request);

        assertNotNull(result);
        assertEquals("java", result.getSkillName());

        verify(userRepository, times(1)).findById(user.getId());
        verify(skillsRepository, times(1)).save(any(Skill.class));
    }

    @Test
    void getSkill(){
        Long skillId = 1L;
        Skill skill = new Skill("java", null);

        when(skillsRepository.findById(skillId)).thenReturn(Optional.of(skill));

        SkillsDto result = skillService.getSkill(skillId);

        assertNotNull(result);
        assertEquals("java", result.getSkillName());

        verify(skillsRepository, times(1)).findById(skillId);

    }

    @Test
    void updateSkill(){
        Long userId = 1L;

        SkillRequest request = new SkillRequest(null, "java", userId);

        User user = new User();
        user.setId(userId);

        Skill updateSkill = new Skill("java", user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(skillsRepository.save(any(Skill.class))).thenReturn(updateSkill);

        SkillsDto result = skillService.updateSkill(request);

        assertNotNull(result);
        assertEquals("java", result.getSkillName());

        verify(userRepository, times(1)).findById(userId);
        verify(skillsRepository, times(1)).save(any(Skill.class));


    }



}