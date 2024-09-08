package com.example.YourCircle.Domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CircleRepository extends
        CrudRepository<Circle, Integer>{

    List<Circle> findAllByOrderByCircleIdAsc();

    @Query("select c.circleName, c.circleDesc, u.userName, u.firstName, u.lastName from Circle c, User u where c.user.userId=:userId and c.user.userId=u.userId order by c.circleName, c.creationDate ASC")
    List<Circle> findByUserId(int userId);

    @Query("select c.circleName, c.circleDesc, u.userName, u.firstName, u.lastName from Circle c, User u where c.circleName like :circleName and c.user.userId=u.userId order by c.circleName, c.creationDate ASC")
    List<Circle> findByCircleName(String circleName);
//
//    @Query("select c.circleName, c.circleDesc, u.userName, u.firstName, u.lastName from Circle c, User u where c.user.userId=:userId and c.user.userId=u.userId order by c.circleName, c.creationDate ASC")
//    List<Circle> findByUserName(String userName);
}
