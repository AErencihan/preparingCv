package com.example.preparingcv.dto;


public class UserDto {

    private String name;
    private String surName;
    private String eMail;

    public UserDto(Builder builder) {
        this.name = builder.name;
        this.surName = builder.surName;
        this.eMail = builder.eMail;
    }

    public String getName() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public static final class Builder {
        private String name;
        private String surName;
        private String eMail;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surName) {
            this.surName = surName;
            return this;
        }

        public Builder eMail(String eMail) {
            this.eMail = eMail;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }


    }
}