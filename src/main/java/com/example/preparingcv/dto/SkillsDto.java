package com.example.preparingcv.dto;

import com.example.preparingcv.model.Skill;

public class SkillsDto {

    private String skillName;

    public SkillsDto(Builder builder) {
        this.skillName = builder.skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


    public static class Builder {
        private String skillName;


        public Builder skillName(String skillName) {
            this.skillName = skillName;
            return this;
        }

        public SkillsDto build(){
            return new SkillsDto(this);
        }

    }
}
