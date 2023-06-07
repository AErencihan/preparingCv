package com.example.preparingcv.dto;

public class UserAboutDto {
    private String birthDay;
    private String phoneNumber;
    private String address;

    public UserAboutDto(Builder builder){
        this.birthDay = builder.birthDay;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;

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

    public static class Builder{
        private String birthDay;
        private String phoneNumber;
        private String address;

        public Builder(){

        }

        public Builder birthDay(String birthDay){
            this.birthDay = birthDay;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }


    }

}






