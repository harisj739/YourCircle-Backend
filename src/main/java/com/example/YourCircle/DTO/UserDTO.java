package com.example.YourCircle.DTO;

public record UserDTO(int userId,
                      String userName,
                      String password,
                      String firstName,
                      String lastName,
                      String email,
                      String phone,
                      char gender,
                      String city,
                      String state,
                      String country,
                      String zip,
                      int age) {
}
