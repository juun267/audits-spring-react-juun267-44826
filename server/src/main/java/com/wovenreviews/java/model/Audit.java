package com.wovenreviews.java.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Instant;

@TypeDef(name = "jsonb", typeClass = JsonType.class)
@Entity
@Table(name = "audits")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String event;

    private String status;

    private String message;

    @Type(type = "jsonb")
    private Object context;

    @Column(name = "created_at")
    private Instant createdAt;

    public Audit() {
    }

    public Audit(Integer userId, String event, String status, String message) {
        this.userId = userId;
        this.event = event;
        this.status = status;
        this.message = message;
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

    public Object getContext() {
        return context;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
