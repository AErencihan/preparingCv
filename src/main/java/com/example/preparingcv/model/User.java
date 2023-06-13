package com.example.preparingcv.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userSurname;
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Experience> experience;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Education> education;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserAbout> userAbout;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Skill> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<UserAbout> getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(List<UserAbout> userAbout) {
        this.userAbout = userAbout;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }



}
