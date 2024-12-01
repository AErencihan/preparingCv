package com.example.preparingcv.dto;


public class UserDto {

    private String name;
    private String surName;
    private String email;
    private Long id;

    public UserDto(){

    }

    public UserDto(Builder builder) {
        this.name = builder.name;
        this.surName = builder.surName;
        this.email = builder.email;
        this.id = builder.id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final class Builder {
        private String name;
        private String surName;
        private String email;
        private Long id;

        public Builder() {
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surName) {
            this.surName = surName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }

    }
}
