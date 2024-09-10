package com.example.YourCircle.Domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CircleUserRepository extends
        CrudRepository<CircleUser, Integer>{

    @Query("select c.user.userName, c.user.firstName, c.user.lastName, c.user.city, c.user.state, c.user.country from CircleUser c, User u where c.circle.circleId=:circleId and c.user.userId=u.userId order by u.firstName, u.lastName asc")
    List<CircleUser> findByCircleId(int circleId);

    @Query("select c.user.userName, c.user.firstName, c.user.lastName, c.user.city, c.user.state, c.user.country from CircleUser c, User u where c.user.userId=u.userId")
    List<CircleUser> findByUserName(String username);
}
