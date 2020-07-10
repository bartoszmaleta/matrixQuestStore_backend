package com.company.model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int buyerId;
    private String buyer;
    private Timestamp date;
    private int awardId;
    private String awardTitle;
    private int price;
    private int userId;
    private Timestamp boughAt;
    private String owner;

    public Transaction(int transactionId, int userId, int awardId, int price, Timestamp boughAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.awardId = awardId;
        this.price = price;
        this.boughAt = boughAt;
    }

    public Transaction(int userId, int awardId, int price, Timestamp boughAt) {
        this.userId = userId;
        this.awardId = awardId;
        this.price = price;
        this.boughAt = boughAt;
    }


    public Transaction(int transactionId, String owner, String awardTitle, int price, Timestamp boughAt) {
        this.transactionId = transactionId;
        this.owner = owner;
        this.awardTitle = awardTitle;
        this.price = price;
        this.boughAt = boughAt;
    }

//    public String toString() {
//        return "Transaction:\n" +
//                "transaction_id: " + this.transactionId +
//                " title: " + this.awardTitle +
//                " data_bought: " + this.boughAt +
//                " price: " + this.price +
//                " owner: " + this.owner;
//    }

    public String toString() {
        return this.transactionId
                + " " + this.awardTitle
                + " " + this.boughAt
                + " " + this.price
                + " " + this.owner;
    }

    public Transaction setBuyerId(int buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public Transaction setBuyer(String buyer) {
        this.buyer = buyer;
        return this;
    }

    public Transaction setDate(Timestamp date) {
        this.date = date;
        return this;
    }

    public Transaction setAwardId(int awardId) {
        this.awardId = awardId;
        return this;
    }

    public Transaction setAwardTitle(String awardTitle) {
        this.awardTitle = awardTitle;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Transaction setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Timestamp getBoughAt() {
        return boughAt;
    }

    public Transaction setBoughAt(Timestamp boughAt) {
        this.boughAt = boughAt;
        return this;
    }

    public Transaction(int buyerId, String buyer, Timestamp date, int awardId, String awardTitle, int price) {
        this.buyerId = buyerId;
        this.buyer = buyer;
        this.date = date;
        this.awardId = awardId;
        this.awardTitle = awardTitle;
        this.price = price;
    }
    public Transaction(int transactionId, int buyerId, String buyer, Timestamp date, int awardId, String awardTitle, int price) {
        this(buyerId, buyer, date, awardId, awardTitle, price);
        this.transactionId = transactionId;
    }

    public int getPrice() {
        return price;
    }

    public Transaction setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getAwardId() {
        return awardId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(int transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getBuyer() {
        return buyer;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getAwardTitle() {
        return awardTitle;
    }
}
