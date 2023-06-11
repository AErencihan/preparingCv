package com.example.preparingcv.service;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.repository.SkillsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {

    private final SkillsRepository skillsRepository;

    public SkillService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public SkillsDto createSkill(Skill skill) {
        var saveSkill = skillsRepository.save(skill);

        return new SkillsDto.Builder()
                .skillName(saveSkill.getSkillName())
                .build();
    }

    public SkillsDto updateSkill(Skill skill) {
        skillsRepository.findById(skill.getSkillsId()).orElseThrow();

        Skill saved = skillsRepository.save(skill);
        return new SkillsDto.Builder()
                .skillName(saved.getSkillName())
                .build();

    }


    public void deleteSkill(Long skillId) {
        boolean exists = skillsRepository.existsById(skillId);

        Optional.of(exists).ifPresentOrElse(a-> skillsRepository.deleteById(skillId), ()->{
            throw new RuntimeException();
        });
    }
}













