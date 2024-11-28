package com.example.preparingcv.service;

import com.example.preparingcv.dto.EducationDto;
import com.example.preparingcv.dto.ExperienceDto;
import com.example.preparingcv.dto.request.EducationRequest;
import com.example.preparingcv.model.Education;
import com.example.preparingcv.model.User;
import com.example.preparingcv.repository.EducationRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.Test;

import javax.transaction.TransactionScoped;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EducationServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final EducationRepository educationRepository = mock(EducationRepository.class);
    private final EducationService educationService = new EducationService(educationRepository, userRepository);

    @Test
    void createEducation(){
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        EducationRequest request = new EducationRequest("gelisim", "Lisans", user.getId(), null);
        Education education = new Education(user, "gelisim", "Lisans");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(educationRepository.save(any(Education.class))).thenReturn(education);

        EducationDto result = educationService.createEducation(request);

        assertNotNull(result);
        assertEquals(request.getSchoolName(), result.getSchoolName());
        assertEquals(request.getDegree(), result.getDegree());

        verify(userRepository, times(1)).findById(user.getId());
        verify(educationRepository, times(1)).save(any(Education.class));

    }

    @Test
    void getEducation(){
        Long educationId = 1L;
        Education education = new Education(null, "gelisim", "Lisans");

        when(educationRepository.findById(educationId)).thenReturn(Optional.of(education));

        EducationDto result = educationService.getEducation(educationId);

        assertNotNull(result);
        assertEquals(education.getSchoolName(), result.getSchoolName());
        assertEquals(education.getDegree(), result.getDegree());

        verify(educationRepository, times(1)).findById(educationId);

    }

    @Test
    void updateEducation(){
        Long educationId = 1L;

        EducationRequest request = new EducationRequest("gelisim", "Lisans", null, educationId);
        Education education = new Education(null, "gelisim", "Lisans");

        when(educationRepository.findById(educationId)).thenReturn(Optional.of(education));
        when(educationRepository.save(any(Education.class))).thenReturn(education);

        EducationDto result = educationService.updateEducation(request);

        assertNotNull(result);
        assertEquals(request.getSchoolName(), result.getSchoolName());
        assertEquals(request.getDegree(), result.getDegree());

        verify(educationRepository, times(1)).findById(educationId);
        verify(educationRepository, times(1)).save(any(Education.class));

    }

}