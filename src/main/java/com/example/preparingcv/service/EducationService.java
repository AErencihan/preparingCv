package com.example.preparingcv.service;

import com.example.preparingcv.dto.EducationDto;
import com.example.preparingcv.model.Education;
import com.example.preparingcv.repository.EducationRepository;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public EducationDto createEducation(Education education){
        var saveEducation = educationRepository.save(education);

        return new EducationDto.Builder()
                .degree(saveEducation.getDegree())
                .schoolName(saveEducation.getSchoolName())
                .build();
    }


}
