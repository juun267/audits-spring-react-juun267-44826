package com.wovenreviews.java.dto;

public class UserResponse {

    private String email;
    private String firstNAme;
    private String lastName;

    public UserResponse(String email, String firstNAme, String lastName) {
        this.email = email;
        this.firstNAme = firstNAme;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstNAme() {
        return firstNAme;
    }

    public String getLastName() {
        return lastName;
    }
}
