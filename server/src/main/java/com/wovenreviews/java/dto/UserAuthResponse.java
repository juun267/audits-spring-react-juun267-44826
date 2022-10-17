package com.wovenreviews.java.dto;

public class UserAuthResponse {
    private UserResponse user;
    private String token;

    public UserAuthResponse(UserResponse user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
