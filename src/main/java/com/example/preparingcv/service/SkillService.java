package com.example.preparingcv.service;

import com.example.preparingcv.repository.SkillsRepository;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    private final SkillsRepository skillsRepository;

    public SkillService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }
}
