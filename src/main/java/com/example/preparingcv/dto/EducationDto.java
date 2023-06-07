package com.example.preparingcv.dto;

public class EducationDto {
    private String schoolName;
    private String degree;

    public EducationDto(Builder builder) {
        this.degree = builder.degree;
        this.schoolName = builder.schoolName;
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


    public static final class Builder {
        private String schoolName;
        private String degree;

        public Builder() {

        }

        public Builder schoolName(String schoolName) {
            this.schoolName = schoolName;
            return this;
        }

        public Builder degree(String degree) {
            this.degree = degree;
            return this;
        }

        public EducationDto build() {
            return new EducationDto(this);
        }


    }
}







