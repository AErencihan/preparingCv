package com.example.preparingcv.dto.request;

public class UserRequest {
    private Long id;
    private String userName;
    private String userSurname;
    private String email;


    public UserRequest(){

    }

    public UserRequest(Long id, String userName, String userSurname, String email) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
    }

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
}
