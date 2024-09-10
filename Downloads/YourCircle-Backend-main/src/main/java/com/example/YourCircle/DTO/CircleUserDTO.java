package com.example.YourCircle.DTO;

public record CircleUserDTO(int circleId,
                            int userInCircle,
                            String userName,
                            String ownerFirstName,
                            String ownerLastName,
                            String circleName,
                            String circleDesc) {
}
