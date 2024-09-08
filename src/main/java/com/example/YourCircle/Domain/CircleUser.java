package com.example.YourCircle.Domain;

import jakarta.persistence.*;

@Entity
@IdClass(CircleUserId.class)
public class CircleUser {
    @Id
    @ManyToOne
    @JoinColumn(name="circleId", nullable=false)
    private Circle circle;
    @Id
    @ManyToOne
    @JoinColumn(name="userInCircle", nullable=true)
    private User user;

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
