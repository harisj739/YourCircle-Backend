package com.example.YourCircle.DTO;

import java.sql.Date;

public record ItemDTO(int itemId,
                      String itemName,
                      String itemDesc,
                      int circleId,
                      int senderId,
                      int receiverId,
                      Date sendDate,
                      Date receiveDate,
                      int requested,
                      String itemType,
                      String itemCondition) {
}
