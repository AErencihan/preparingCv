package com.example.preparingcv.service;

import com.example.preparingcv.dto.ExperienceDto;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.Experience;
import com.example.preparingcv.repository.ExperienceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public ExperienceDto createExperience(Experience experience) {
        var saveExperience = experienceRepository.save(experience);

        return new ExperienceDto.Builder()
                .companyName(saveExperience.getCompanyName())
                .position(experience.getPosition())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .build();
    }

    public Experience getExperience(Long experienceId){
        return experienceRepository.findById(experienceId).orElseThrow(()-> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("no information about the Experience")
                .build());
    }

    public ExperienceDto updateExperience(Experience experience) {
        experienceRepository.findById(experience.getExperienceId())
                .orElseThrow(()-> new GenericException.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("no information for update")
                        .build());

        Experience saved = experienceRepository.save(experience);

        return new ExperienceDto.Builder()
                .endDate(saved.getEndDate())
                .startDate(saved.getStartDate())
                .position(saved.getPosition())
                .companyName(saved.getCompanyName())
                .build();
    }

    public void deleteExperience(Long experienceId) {
        boolean exists = experienceRepository.existsById(experienceId);

        Optional.of(exists).ifPresentOrElse(a-> experienceRepository.deleteById(experienceId), ()->{
            new GenericException.Builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("No Experience for delete")
                    .build();
        });

    }

}
