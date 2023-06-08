package com.example.preparingcv.service;

import com.example.preparingcv.dto.ExperienceDto;
import com.example.preparingcv.model.Experience;
import com.example.preparingcv.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public ExperienceDto createExperience(Experience experience){
        var saveExperience = experienceRepository.save(experience);

        return new ExperienceDto.Builder()
                .companyName(saveExperience.getCompanyName())
                .position(experience.getPosition())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .build();
    }
}
