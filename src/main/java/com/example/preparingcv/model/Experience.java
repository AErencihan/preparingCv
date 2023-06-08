package com.example.preparingcv.model;

import javax.persistence.*;

@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String companyName;
    private String position;
    private String startDate;
    private String endDate;

    public Long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Long experienceId) {
        this.experienceId = experienceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}

