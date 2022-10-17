package com.wovenreviews.java.dto;


import java.time.Instant;

public class AuditsResponse {

    private Integer id;

    private Integer userId;

    private String event;

    private String status;

    private String message;

    private Instant createdAt;

    public AuditsResponse() {
    }

    public AuditsResponse(Integer id, Integer userId, String event, String status, String message, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.event = event;
        this.status = status;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEvent() {
        return event;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
