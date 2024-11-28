package com.example.preparingcv.dto.request;

public class EducationRequest {
    private String schoolName;
    private String degree;
    private Long userId;
    private Long educationId;

    public EducationRequest(String schoolName, String degree, Long userId, Long educationId) {
        this.schoolName = schoolName;
        this.degree = degree;
        this.userId = userId;
        this.educationId = educationId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }
}
