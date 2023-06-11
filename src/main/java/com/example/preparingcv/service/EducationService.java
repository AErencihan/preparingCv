package com.example.preparingcv.service;

import com.example.preparingcv.dto.EducationDto;
import com.example.preparingcv.model.Education;
import com.example.preparingcv.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public EducationDto createEducation(Education education) {
        var saveEducation = educationRepository.save(education);

        return new EducationDto.Builder()
                .degree(saveEducation.getDegree())
                .schoolName(saveEducation.getSchoolName())
                .build();
    }

    public Education getEducation(Long educationId) {
        return educationRepository.findById(educationId).orElseThrow();
    }

    public EducationDto updateEducation(Education education) {
        educationRepository.findById(education.getEducationId())
                .orElseThrow();

        Education saved = educationRepository.save(education);
        return new EducationDto.Builder()
                .schoolName(saved.getSchoolName())
                .degree(saved.getDegree())
                .build();
    }


    public void deleteEducation(Long educationId) {
        boolean exists = educationRepository.existsById(educationId);

        Optional.of(exists).ifPresentOrElse(a -> educationRepository.deleteById(educationId), () -> {
            throw new RuntimeException();
        });
    }
}
