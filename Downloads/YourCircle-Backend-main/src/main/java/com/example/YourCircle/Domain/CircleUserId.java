package com.example.YourCircle.Domain;

import java.io.Serializable;
import java.util.Objects;

public class CircleUserId implements Serializable {

    private Long circle;
    private Long user;

    public CircleUserId() {}

    public CircleUserId(Long circle, Long user) {
        this.circle = circle;
        this.user = user;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CircleUserId that = (CircleUserId) o;
//        return Objects.equals(circle, that.circle) && Objects.equals(user, that.user);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(circle, user);
//    }
}
