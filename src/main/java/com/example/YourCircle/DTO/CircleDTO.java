package com.example.YourCircle.DTO;

public record CircleDTO(int circleId,
                        int ownerId,
                        String ownerUserName,
                        String ownerFirstName,
                        String ownerLastName,
                        String circleName,
                        String circleDesc) {
}
