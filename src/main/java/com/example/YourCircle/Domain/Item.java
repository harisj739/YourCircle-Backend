package com.example.YourCircle.Domain;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int itemId;   // unique id generated by database.
    private String itemName;
    private String itemDesc;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public int getRequested() {
        return requested;
    }

    public void setRequested(int requested) {
        this.requested = requested;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    @ManyToOne
    @JoinColumn(name="circleId", nullable=false)
    private Circle circle;
    @ManyToOne
    @JoinColumn(name="senderId", nullable=false)
    private User sender;
    @ManyToOne
    @JoinColumn(name="receiverId", nullable=true)
    private User receiver;
    private Date sendDate;
    private Date receiveDate;
    private int requested;
    private String itemType;
    private String itemCondition;
}
