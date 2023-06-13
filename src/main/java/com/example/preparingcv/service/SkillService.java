package com.example.preparingcv.service;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.repository.SkillsRepository;
import org.springframework.http.HttpStatus;
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

    public Skill getSkill(Long skillId){
        return skillsRepository.findById(skillId).orElseThrow(()-> new GenericException.Builder()
                .message("skill Not found")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
    }

    public SkillsDto updateSkill(Skill skill) {
        skillsRepository.findById(skill.getSkillsId()).orElseThrow(()-> new GenericException.Builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("no information for update")
                .build());

        Skill saved = skillsRepository.save(skill);
        return new SkillsDto.Builder()
                .skillName(saved.getSkillName())
                .build();

    }


    public void deleteSkill(Long skillId) {
        boolean exists = skillsRepository.existsById(skillId);

        Optional.of(exists).ifPresentOrElse(a-> skillsRepository.deleteById(skillId), ()->{
            new GenericException.Builder()
                    .message("No found skill for delete")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        });
    }
}













