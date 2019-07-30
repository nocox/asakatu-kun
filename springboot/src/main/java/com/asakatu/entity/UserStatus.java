package com.asakatu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_status")
public class UserStatus {
    @Id
    @Column(name = "user_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_status_master_id")
    private Long masterId;

    @Column(name = "user_status_comment")
    private String comment;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public User getUser() {
//        return user;
//    }
//
    public void setUser(User user) {
        this.user = user;
    }

//    public Event getEvent() {
//        return event;
//    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
