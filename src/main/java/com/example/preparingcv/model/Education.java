package com.example.preparingcv.model;

import javax.persistence.*;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long educationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String schoolName;
    private String degree;

}
