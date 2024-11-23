package com.example.preparingcv.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public GenericException(Builder builder) {
        this.message = builder.message;
        this.httpStatus = builder.httpStatus;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public static final class Builder{
        private String message;
        private HttpStatus httpStatus;


        public Builder(){

        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus){
            this.httpStatus = httpStatus;
            return this;
        }
        public GenericException build(){
            return new GenericException(this);
        }


    }




}











