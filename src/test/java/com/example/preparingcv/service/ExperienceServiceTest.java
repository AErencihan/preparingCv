package com.example.preparingcv.service;

import com.example.preparingcv.dto.ExperienceDto;
import com.example.preparingcv.dto.request.ExperienceRequest;
import com.example.preparingcv.model.Experience;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.ExperienceRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExperienceServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final ExperienceRepository experienceRepository = mock(ExperienceRepository.class);
    private final ExperienceService experienceService = new ExperienceService(experienceRepository, userRepository);


    @Test
    void createExperience(){
        Long userId = 1L;
        User user = new User();
        user.setId(1L);

        ExperienceRequest request = new ExperienceRequest("apple", "developer", "01.01.2000",
                "present", user.getId(), null);

        Experience experience = new Experience(user, "apple", "developer", "01.01.2000",
                "present");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(experienceRepository.save(any(Experience.class))).thenReturn(experience);

        ExperienceDto result = experienceService.createExperience(request);

        assertNotNull(result);
        assertEquals(request.getCompanyName(), result.getCompanyName());
        assertEquals(request.getPosition(), result.getPosition());
        assertEquals(request.getStartDate(), result.getStartDate());
        assertEquals(request.getEndDate(), result.getEndDate());

    }


    @Test
    void getExperience(){
        Long experienceId = 1L;

        Experience experience = new Experience(null, "apple", "developer", "01.01.2000",
                "present");

        when(experienceRepository.findById(experienceId)).thenReturn(Optional.of(experience));

        ExperienceDto result = experienceService.getExperience(experienceId);

        assertNotNull(result);
        assertEquals("apple", result.getCompanyName());
        assertEquals("developer", result.getPosition());
        assertEquals("01.01.2000", result.getStartDate());
        assertEquals("present", result.getEndDate());

        verify(experienceRepository, times(1)).findById(experienceId);

    }


    @Test
    void updateExperience(){
        Long experienceId = 1L;
        ExperienceRequest request = new ExperienceRequest("apple", "developer", "01.01.2000",
                "present",null, experienceId);


        Experience experience = new Experience(null, "apple", "developer", "01.01.2000",
                "present");


        when(experienceRepository.findById(experienceId)).thenReturn(Optional.of(experience));
        when(experienceRepository.save(any(Experience.class))).thenReturn(experience);

        ExperienceDto result = experienceService.updateExperience(request);

        assertNotNull(result);
        assertEquals(request.getCompanyName(), result.getCompanyName());
        assertEquals(request.getPosition(), result.getPosition());
        assertEquals(request.getStartDate(), result.getStartDate());
        assertEquals(request.getEndDate(), result.getEndDate());

        verify(experienceRepository, times(1)).findById(experienceId);
        verify(experienceRepository, times(1)).save(any(Experience.class));

    }



}