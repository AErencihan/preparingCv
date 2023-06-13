package com.example.preparingcv.dto.request;

public class UserAboutRequest {
    private Long userAboutId;
    private String birthDay;
    private String phoneNumber;
    private String address;
    private Long userId;

    public UserAboutRequest(Long userAboutId, String birthDay, String phoneNumber, String address, Long userId) {
        this.userAboutId = userAboutId;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userId = userId;
    }

    public UserAboutRequest() {
    }

    public Long getUserAboutId() {
        return userAboutId;
    }

    public void setUserAboutId(Long userAboutId) {
        this.userAboutId = userAboutId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
