package com.example.preparingcv.dto;


public class ExperienceDto {
    private String companyName;
    private String position;
    private String startDate;
    private String endDate;

    public ExperienceDto(Builder builder) {
        this.companyName = builder.companyName;
        this.position = builder.position;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static class Builder {
        private String companyName;
        private String position;
        private String startDate;
        private String endDate;

        public Builder() {
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public ExperienceDto build() {
            return new ExperienceDto(this);
        }

    }
}
