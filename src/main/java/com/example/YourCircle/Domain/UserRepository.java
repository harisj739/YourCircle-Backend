package com.example.YourCircle.Domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends
        CrudRepository<User, Integer>{

    List<User> findAllByOrderByUserIdAsc();

    @Query("select u.userName, u.firstName, u.lastName, u.city, u.state, u.country from User u where u.userName like :userName")
    List<User> findByUserName(String userName);

    @Query("select u.userName, u.firstName, u.lastName, u.city, u.state, u.country from User u where u.firstName like :firstName and u.lastName=:lastName order by u.firstName, u.lastName asc")
    List<User> findByFirstAndLastName(String firstName, String lastName);

    User findByEmail(String email);

    @Query("select u.userName, u.firstName, u.lastName, u.city, u.state, u.country from User u where u.city like :city and u.state=:state and u.country=:country order by u.firstName, u.lastName asc")
    List<User> findByCityAndStateAndCountry(String city, String state, String country);
}
