package com.example.YourCircle.Controller;

import com.example.YourCircle.DTO.CircleDTO;
import com.example.YourCircle.DTO.CircleUserDTO;
import com.example.YourCircle.DTO.UserDTO;
import com.example.YourCircle.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // TODO: Change the Cross Origin link to the website link!
public class CircleUserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CircleRepository circleRepository;

    @Autowired
    CircleUserRepository circleUserRepository;

    @GetMapping("/yourcircle/users")
    public List<CircleUserDTO> findUsersInCircle(@RequestParam ("circleId") int circleId) {

        List<CircleUser> users = circleUserRepository.findByCircleId(circleId);
        List<CircleUserDTO> circleUserDTO_list = new ArrayList<>();
        for (CircleUser u: users) {
            circleUserDTO_list.add(new CircleUserDTO(u.getCircle().getCircleId(),  u.getUser().getUserId(), u.getUser().getUserName(), u.getUser().getFirstName(), u.getUser().getLastName(), u.getCircle().getCircleName(), u.getCircle().getCircleDesc()));
        }
        return circleUserDTO_list;
    }

    @GetMapping("/yourcircle/user")
    public List<CircleUserDTO> findUserInCircle(@RequestParam ("userName") String userName) {

        List<CircleUser> user = circleUserRepository.findByUserName(userName);
        List<CircleUserDTO> circleUserDTO_list = new ArrayList<>();
        circleUserDTO_list.add(new CircleUserDTO(user.get(0).getCircle().getCircleId(),  user.get(0).getUser().getUserId(), user.get(0).getUser().getUserName(), user.get(0).getUser().getFirstName(), user.get(0).getUser().getLastName(), user.get(0).getCircle().getCircleName(), user.get(0).getCircle().getCircleDesc()));
        return circleUserDTO_list;
    }

    @PutMapping("/yourcircle/user")
    public CircleUserDTO addUserToCircle(@RequestBody CircleUserDTO circleUserDTO) {
        User user = userRepository.findById(circleUserDTO.userInCircle()).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: User not found!");
        }

        Circle circle = circleRepository.findById(circleUserDTO.circleId()).orElse(null);

        if (circle == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Circle does not exist!");
        } //if

        CircleUser newCircleUser = new CircleUser();

        newCircleUser.setUser(user);
        newCircleUser.setCircle(circle);

        circleUserRepository.save(newCircleUser);
        return new CircleUserDTO(newCircleUser.getCircle().getCircleId(), newCircleUser.getUser().getUserId(), newCircleUser.getUser().getUserName(), newCircleUser.getUser().getFirstName(), newCircleUser.getUser().getLastName(), newCircleUser.getCircle().getCircleName(), newCircleUser.getCircle().getCircleDesc());
    }

    @DeleteMapping("/yourCircle/user/{userName}")
    public void  removeUserFromCircle(@PathVariable("userName") String userName) {
        List<CircleUser> deleteUser = circleUserRepository.findByUserName(userName);

        if (!deleteUser.isEmpty()) {
            circleUserRepository.delete(deleteUser.get(0));
        } //if
    }
}