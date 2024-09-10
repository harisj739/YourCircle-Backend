package com.example.YourCircle.Controller;

import com.example.YourCircle.DTO.UserDTO;
import com.example.YourCircle.Domain.User;
import com.example.YourCircle.Domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://circle-weld-iota.vercel.app")
public class UserController {

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {

        List<User> users = userRepository.findAllByOrderByUserIdAsc();
        List<UserDTO> userDTO_list = new ArrayList<>();
        for (User u: users) {
            userDTO_list.add(new UserDTO(u.getUserId(), u.getUserName(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), u.getGender(), u.getCity(), u.getState(), u.getCountry(), u.getZip(), u.getAge()));
        }
        return userDTO_list;
    }

    @GetMapping("/user/username")
    public List<UserDTO> findUserByUserName(@RequestParam("userName") String userName) {

        List<User> users = userRepository.findByUserName(userName);
        List<UserDTO> userDTO_list = new ArrayList<>();
        for (User u: users) {
            userDTO_list.add(new UserDTO(u.getUserId(), u.getUserName(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), u.getGender(), u.getCity(), u.getState(), u.getCountry(), u.getZip(), u.getAge()));
        }
        return userDTO_list;
    }

    @GetMapping("/user/name")
    public List<UserDTO> findUserByFullName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        List<User> users = userRepository.findByFirstAndLastName(firstName, lastName);
        List<UserDTO> userDTO_list = new ArrayList<>();
        for (User u: users) {
            userDTO_list.add(new UserDTO(u.getUserId(), u.getUserName(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), u.getGender(), u.getCity(), u.getState(), u.getCountry(), u.getZip(), u.getAge()));
        }
        return userDTO_list;
    }

    @GetMapping("/user/location")
    public List<UserDTO> findUserByCityStateAndCountry(@RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("country") String country) {

        List<User> users = userRepository.findByCityAndStateAndCountry(city, state, country);
        List<UserDTO> userDTO_list = new ArrayList<>();
        for (User u: users) {
            userDTO_list.add(new UserDTO(u.getUserId(), u.getUserName(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), u.getGender(), u.getCity(), u.getState(), u.getCountry(), u.getZip(), u.getAge()));
        }
        return userDTO_list;
    }

    @PostMapping("/user/register")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // Each username must be unique; if the username exists, then throw
        List<User> existingUser = userRepository.findByUserName(userDTO.userName());
        if (!existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username @" + userDTO.userName() + " already exists!");
        }
        User user = new User();
        user.setUserName(userDTO.userName());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setPhone(userDTO.phone());
        user.setGender(userDTO.gender());
        user.setCity(userDTO.city());
        user.setState(userDTO.state());
        user.setCountry(userDTO.country());
        user.setZip(userDTO.zip());
        user.setAge(userDTO.age());

        // Encrypt password.
        String enc_password = encoder.encode(userDTO.password());
        user.setPassword(enc_password);

        userRepository.save(user);
        return new UserDTO(user.getUserId(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getGender(), user.getCity(), user.getState(), user.getCountry(), user.getZip(), user.getAge());
    }

    @PutMapping("/user")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        User updateUser = userRepository.findById(userDTO.userId()).orElse(null);

        if (updateUser==null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: User @" + userDTO.userName() + " does not exist!");
        } //if

        updateUser.setUserName(userDTO.userName());
        updateUser.setFirstName(userDTO.firstName());
        updateUser.setLastName(userDTO.lastName());
        updateUser.setEmail(userDTO.email());
        updateUser.setPhone(userDTO.phone());
        updateUser.setGender(userDTO.gender());
        updateUser.setCity(userDTO.city());
        updateUser.setState(userDTO.state());
        updateUser.setCountry(userDTO.country());
        updateUser.setZip(userDTO.zip());
        updateUser.setAge(userDTO.age());

        // Encrypt password.
        String enc_password = encoder.encode(userDTO.password());
        updateUser.setPassword(enc_password);

        userRepository.save(updateUser);

        return new UserDTO(updateUser.getUserId(), updateUser.getUserName(), updateUser.getPassword(), updateUser.getFirstName(), updateUser.getLastName(), updateUser.getEmail(), updateUser.getPhone(), updateUser.getGender(), updateUser.getCity(), updateUser.getState(), updateUser.getCountry(), updateUser.getZip(), updateUser.getAge());
    }

    @DeleteMapping("/users/{userId}")
    public void  updateUser(@PathVariable("userId") int userId) {
        User deleteUser = userRepository.findById(userId).orElse(null);

        if (deleteUser!=null) {
            userRepository.delete(deleteUser);
        } //if
    }
}