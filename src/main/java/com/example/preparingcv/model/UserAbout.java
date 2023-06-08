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

    public Long getUserAboutId() {
        return userAboutId;
    }

    public void setUserAboutId(Long userAboutId) {
        this.userAboutId = userAboutId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
