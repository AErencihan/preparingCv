package com.example.preparingcv.service;

import com.example.preparingcv.dto.EducationDto;
import com.example.preparingcv.dto.request.EducationRequest;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.Education;
import com.example.preparingcv.repository.EducationRepository;
import com.example.preparingcv.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationService(EducationRepository educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public EducationDto createEducation(EducationRequest education) {
        var user = userRepository.findById(education.getUserId())
                .orElseThrow(() -> new GenericException.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("Not found User ")
                        .build());

        Education newEducation = new Education(user, education.getSchoolName(), education.getDegree());

        var saveEducation = educationRepository.save(newEducation);

        return new EducationDto.Builder()
                .degree(saveEducation.getDegree())
                .schoolName(saveEducation.getSchoolName())
                .build();
    }

    public EducationDto getEducation(Long educationId) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("no information about the user")
                .build());

        return new EducationDto.Builder()
                .degree(education.getDegree())
                .schoolName(education.getSchoolName())
                .build();
    }

    public EducationDto updateEducation(EducationRequest education) {
        var savedEducation = education.getEducationId();

        var educationUpdate = educationRepository.findById(savedEducation)
                .orElseThrow(() -> new GenericException.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("no information about the user")
                        .build());

        educationUpdate.setDegree(education.getDegree());
        educationUpdate.setSchoolName(education.getSchoolName());

        var saveEducation = educationRepository.save(educationUpdate);

        return new EducationDto.Builder()
                .degree(saveEducation.getDegree())
                .schoolName(saveEducation.getSchoolName())
                .build();
    }


    public void deleteEducation(Long educationId) {
        boolean exists = educationRepository.existsById(educationId);

        if (!exists) {
            throw new GenericException.Builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("No user Delete was found")
                    .build();
        }
        educationRepository.deleteById(educationId);
    }
}
