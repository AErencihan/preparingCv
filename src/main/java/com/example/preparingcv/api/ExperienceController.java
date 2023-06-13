package com.example.preparingcv.api;

import com.example.preparingcv.dto.ExperienceDto;
import com.example.preparingcv.dto.request.ExperienceRequest;
import com.example.preparingcv.model.Experience;
import com.example.preparingcv.service.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/experience")
@RestController
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping("/save")
    public ResponseEntity<ExperienceDto> saveExperience(@RequestBody ExperienceRequest experience){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(experienceService.createExperience(experience));
    }

    @DeleteMapping("/delete")
    public void deleteExperience(Long experienceId){
        experienceService.deleteExperience(experienceId);
    }
    @PutMapping("/update")
    public ExperienceDto updateExperience(@RequestBody ExperienceRequest experience){
        return experienceService.updateExperience(experience);
    }

    @GetMapping("/get/{experienceId}")
    public Experience getExperience(@PathVariable Long experienceId){
        return experienceService.getExperience(experienceId);
    }



}
