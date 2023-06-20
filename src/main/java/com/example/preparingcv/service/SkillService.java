package com.example.preparingcv.service;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.dto.request.SkillRequest;
import com.example.preparingcv.exception.GenericException;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.repository.SkillsRepository;
import com.example.preparingcv.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {

    private final SkillsRepository skillsRepository;
    private final UserRepository userRepository;

    public SkillService(SkillsRepository skillsRepository, UserRepository userRepository) {
        this.skillsRepository = skillsRepository;
        this.userRepository = userRepository;
    }

    public SkillsDto createSkill(SkillRequest skill) {
        var user = userRepository.findById(skill.getUserId()).orElseThrow(() -> new GenericException.Builder()
                .message("user not found")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());

        Skill saved = skillsRepository.save(new Skill(
                skill.getSkillName(),
                user
        ));

        return new SkillsDto.Builder()
                .skillName(saved.getSkillName())
                .build();

    }

    public SkillsDto getSkill(Long skillId) {
        Skill skill = skillsRepository.findById(skillId).orElseThrow(() -> new GenericException.Builder()
                .message("skill Not found")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());

        return new SkillsDto.Builder()
                .skillName(skill.getSkillName())
                .build();
    }

    public SkillsDto updateSkill(SkillRequest skill) {
        var user = userRepository.findById(skill.getUserId()).orElseThrow(() -> new GenericException.Builder()
                .message("user not found")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());

        Skill saved = skillsRepository.save(new Skill(
                skill.getSkillName(),
                user
        ));

        return new SkillsDto.Builder()
                .skillName(saved.getSkillName())
                .build();
    }


    public void deleteSkill(Long skillId) {
        boolean exists = skillsRepository.existsById(skillId);

        if (!exists) {
            throw new GenericException.Builder()
                    .message("No user Delete was found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        skillsRepository.deleteById(skillId);
    }
}













