package com.example.preparingcv.service;

import com.example.preparingcv.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;

    }
}
