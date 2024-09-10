package com.example.YourCircle.Controller;

import com.example.YourCircle.DTO.CircleDTO;
import com.example.YourCircle.Domain.Circle;
import com.example.YourCircle.Domain.CircleRepository;
import com.example.YourCircle.Domain.User;
import com.example.YourCircle.Domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://circle-weld-iota.vercel.app")
public class CircleController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CircleRepository circleRepository;

    @GetMapping("/yourcircles")
    public List<CircleDTO> findUserCircles(@RequestParam ("userId") int userId) {

        List<Circle> circles = circleRepository.findByUserId(userId);
        List<CircleDTO> circleDTO_list = new ArrayList<>();
        for (Circle c: circles) {
            circleDTO_list.add(new CircleDTO(c.getCircleId(), c.getUser().getUserId(), c.getUser().getUserName(), c.getUser().getFirstName(), c.getUser().getLastName(), c.getCircleName(), c.getCircleDesc()));
        }
        return circleDTO_list;
    }

    @GetMapping("/circles")
    public List<CircleDTO> findCircleByName(@RequestParam ("circleName") String circleName) {

        List<Circle> circles = circleRepository.findByCircleName(circleName);
        List<CircleDTO> circleDTO_list = new ArrayList<>();
        for (Circle c: circles) {
            circleDTO_list.add(new CircleDTO(c.getCircleId(), c.getUser().getUserId(), c.getUser().getUserName(), c.getUser().getFirstName(), c.getUser().getLastName(), c.getCircleName(), c.getCircleDesc()));
        }
        return circleDTO_list;
    }

    @PostMapping("/yourcircle/create")
    public CircleDTO createCircle(@RequestBody CircleDTO circleDTO) {
        User user = userRepository.findById(circleDTO.ownerId()).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: User @" + circleDTO.ownerUserName() + "not found!");
        }

        Circle circle = new Circle();
        circle.setUser(user);
        circle.setCircleName(circleDTO.circleName());
        circle.setCircleDesc(circleDTO.circleDesc());

        circleRepository.save(circle);
        return new CircleDTO(circle.getCircleId(), circle.getUser().getUserId(), circle.getUser().getUserName(), circle.getUser().getFirstName(), circle.getUser().getLastName(), circle.getCircleName(), circle.getCircleDesc());
    }

    @PutMapping("/yourcircle")
    public CircleDTO updateCircle(@RequestBody CircleDTO circleDTO) {
        Circle updateCircle = circleRepository.findById(circleDTO.circleId()).orElse(null);

        if (updateCircle == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Circle '" + circleDTO.circleName() + "' does not exist!");
        } //if

        updateCircle.setCircleName(circleDTO.circleName());
        updateCircle.setCircleDesc(circleDTO.circleDesc());

        circleRepository.save(updateCircle);
        return new CircleDTO(updateCircle.getCircleId(), updateCircle.getUser().getUserId(), updateCircle.getUser().getUserName(), updateCircle.getUser().getFirstName(), updateCircle.getUser().getLastName(), updateCircle.getCircleName(), updateCircle.getCircleDesc());
    }

    @DeleteMapping("/users/{circleId}")
    public void  deleteUserCircle(@PathVariable("circleId") int circleId) {
        Circle deleteCircle = circleRepository.findById(circleId).orElse(null);

        if (deleteCircle != null) {
            circleRepository.delete(deleteCircle);
        } //if
    }
}