package com.example.preparingcv.model;

import javax.persistence.*;

@Entity
@Table(name = "user_about")
public class UserAbout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAboutId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private String birthDay;
    private String phoneNumber;
    private String address;


}
