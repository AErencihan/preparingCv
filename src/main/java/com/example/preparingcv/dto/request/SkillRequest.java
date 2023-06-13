package com.example.preparingcv.dto.request;

public class SkillRequest {
    private Long skillsId;

    private String skillName;
    private Long userId;

    public SkillRequest(Long skillsId, String skillName, Long userId) {
        this.skillsId = skillsId;
        this.skillName = skillName;
        this.userId = userId;
    }

    public SkillRequest() {
    }

    public Long getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Long skillsId) {
        this.skillsId = skillsId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
