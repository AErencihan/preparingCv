package com.example.preparingcv.model;

import javax.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsId;

    private String skillName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Skill(String skillName, User user) {
        this.skillName = skillName;
        this.user = user;
    }

    public Skill() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
