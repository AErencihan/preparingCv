package com.example.preparingcv.api;

import com.example.preparingcv.dto.request.EducationRequest;
import com.example.preparingcv.model.Education;
import com.example.preparingcv.repository.EducationRepository;
import com.example.preparingcv.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EductionControllerTest extends BaseIntegrationTest {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        educationRepository.deleteAll();
    }

    @Test
    void saveEducation() throws Exception {
        Long userId = createUser();

        EducationRequest request = new EducationRequest("a", "computer engineering", userId,
                null);

        mvc.perform(MockMvcRequestBuilders.post("/api/education/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolName").value(request.getSchoolName()))
                .andExpect(jsonPath("$.degree").value(request.getDegree()))
                .andReturn();
    }

    @Test
    void deleteEducation() throws Exception {
        Long userId = createUser();

        EducationRequest request = new EducationRequest("a", "computer engineering", userId,
                null);

        mvc.perform(MockMvcRequestBuilders.post("/api/education/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolName").value(request.getSchoolName()))
                .andExpect(jsonPath("$.degree").value(request.getDegree()))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders.delete("/api/education/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Optional<Education> education = educationRepository.findById(request.getUserId());
        assertTrue(education.isEmpty());

    }

    @Test
    void updateEducation() throws Exception {
        Long userId = createUser();

        EducationRequest request = new EducationRequest("a", "computer engineering", userId,
                null);

        mvc.perform(MockMvcRequestBuilders.post("/api/education/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolName").value(request.getSchoolName()))
                .andExpect(jsonPath("$.degree").value(request.getDegree()))
                .andReturn();

        Optional<Education> education = educationRepository.findById(userId);
        Education savedEducation = education.get();

        EducationRequest request1 = new EducationRequest("b", "software engineering", userId,
                savedEducation.getEducationId());

        mvc.perform(MockMvcRequestBuilders.put("/api/education/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolName").value(request1.getSchoolName()))
                .andExpect(jsonPath("$.degree").value(request1.getDegree()))
                .andReturn();
    }

    @Test
    void getEducation() throws Exception {
        Long userId = createUser();

        EducationRequest request = new EducationRequest("gelisim", "computer engineering", userId,
                null);

        mvc.perform(MockMvcRequestBuilders.post("/api/education/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolName").value(request.getSchoolName()))
                .andExpect(jsonPath("$.degree").value(request.getDegree()))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders.get("/api/education/get/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolName").value(request.getSchoolName()))
                .andExpect(jsonPath("$.degree").value(request.getDegree()))
                .andReturn();

    }

}