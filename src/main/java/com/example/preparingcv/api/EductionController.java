package com.example.preparingcv.api;

import com.example.preparingcv.dto.EducationDto;
import com.example.preparingcv.dto.request.EducationRequest;
import com.example.preparingcv.model.Education;
import com.example.preparingcv.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/education")
@RestController
public class EductionController {

    private final EducationService educationService;

    public EductionController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/save")
    public ResponseEntity<EducationDto> saveEducation(@RequestBody EducationRequest education) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(educationService.createEducation(education));
    }

    @DeleteMapping("/delete/{educationId}")
    public void deleteEducation(@PathVariable Long educationId) {
        educationService.deleteEducation(educationId);
    }

    @PutMapping("/update")
    public EducationDto updateEducation(@RequestBody EducationRequest education) {
        return educationService.updateEducation(education);
    }

    @GetMapping("/get/{educationId}")
    public EducationDto getEducation(@PathVariable Long educationId) {
        return educationService.getEducation(educationId);
    }

}
