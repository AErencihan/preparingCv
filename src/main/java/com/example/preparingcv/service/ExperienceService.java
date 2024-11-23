package com.example.preparingcv.service;

import com.example.preparingcv.dto.ExperienceDto;
import com.example.preparingcv.dto.request.ExperienceRequest;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.Experience;
import com.example.preparingcv.repository.ExperienceRepository;
import com.example.preparingcv.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
public class ExperienceService {


    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ExperienceService(ExperienceRepository experienceRepository, UserRepository userRepository) {
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
    }

    public ExperienceDto createExperience(ExperienceRequest experience) {

        userRepository.findById(experience.getUserId())
                .orElseThrow(() -> new GenericException.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("No User for create Experience")
                        .build());

        Experience saved = experienceRepository.save(new Experience(
                userRepository.findById(experience.getUserId()).orElseThrow(() -> new GenericException.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("No User for create Experience")
                        .build()),
                experience.getCompanyName(),
                experience.getPosition(),
                experience.getStartDate(),
                experience.getEndDate()
        ));

        return new ExperienceDto.Builder()
                .companyName(saved.getCompanyName())
                .position(saved.getPosition())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .build();
    }

    public ExperienceDto getExperience(Long experienceId) {
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(() -> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("no information about the Experience")
                .build());

        return new ExperienceDto.Builder()
                .companyName(experience.getCompanyName())
                .position(experience.getPosition())
                .build();
    }

    public ExperienceDto updateExperience(ExperienceRequest experience) {
        Experience experience1 = experienceRepository.findById(experience.getExperienceId()).orElseThrow(() -> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("No Experience for update")
                .build());

        experience1.setCompanyName(experience.getCompanyName());
        experience1.setPosition(experience.getPosition());
        experience1.setStartDate(experience.getStartDate());
        experience1.setEndDate(experience.getEndDate());

        Experience saved = experienceRepository.save(experience1);

        return new ExperienceDto.Builder()
                .companyName(saved.getCompanyName())
                .position(saved.getPosition())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .build();
    }

    public void deleteExperience(Long experienceId) {
        boolean exists = experienceRepository.existsById(experienceId);

        if (!exists) {
            throw new GenericException.Builder()
                    .message("No user Delete was found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        experienceRepository.deleteById(experienceId);
    }

}
