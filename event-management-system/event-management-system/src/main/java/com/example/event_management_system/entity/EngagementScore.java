package com.example.event_management_system.entity;

import jakarta.persistence.*;

@Entity
public class EngagementScore {

    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private int totalPoints = 0;

    // Getters and Setters
    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public int getTotalPoints() { return totalPoints; }

    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }
}
