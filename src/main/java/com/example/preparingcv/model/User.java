package com.example.preparingcv.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userSurname;
    private String eMail;

    @OneToMany
    private List<Experience> experience;

    @OneToMany
    private List<Education> education;

    @OneToMany
    private List<UserAbout> userAbout;

    @OneToMany
    private List<Skill> skills;

}
