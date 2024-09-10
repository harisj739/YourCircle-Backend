package com.example.YourCircle.Domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface ItemRepository extends
        CrudRepository<Item, Integer>{

    List<Item> findAllByOrderByItemIdAsc();

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i, User u where i.sender.userId=:userId and i.sender.userId=u.userId")
    List<Item> findBySender(int userId);

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i where i.circle.circleId=:circleId")
    List<Item> findByCircle(int circleId);

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i where i.itemName=:itemName")
    List<Item> findByItemName(String itemName);

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i where i.itemType=:itemType")
    List<Item> findByItemType(String itemType);

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i where i.itemCondition=:itemCondition")
    List<Item> findByItemCondition(String itemCondition);

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i where i.requested=:requested")
    List<Item> findByRequested(int requested);

    @Query("select i.itemName, i.itemDesc, i.itemType, i.itemCondition from Item i where i.sendDate>=:sendDate and i.receiveDate<=:receiveDate")
    List<Item> findByDateRange(Date sendDate, Date receiveDate);
}
