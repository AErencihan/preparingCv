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


}
