package com.example.preparingcv.api;

import com.example.preparingcv.dto.SkillsDto;
import com.example.preparingcv.dto.request.SkillRequest;
import com.example.preparingcv.model.Skill;
import com.example.preparingcv.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/skill")
@RestController
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/save")
    public ResponseEntity<SkillsDto> saveSkill(@RequestBody SkillRequest skill){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(skillService.createSkill(skill));
    }

    @DeleteMapping("/delete/{skillId}")
    public void deleteSkill(@PathVariable Long skillId){
        skillService.deleteSkill(skillId);
    }

    @PutMapping("/update")
    public SkillsDto updateSkill(@RequestBody SkillRequest skill){
        return skillService.updateSkill(skill);
    }

    @GetMapping("/get/{skillId}")
    public Skill getSkill(@PathVariable Long skillId){
        return skillService.getSkill(skillId);
    }
}











