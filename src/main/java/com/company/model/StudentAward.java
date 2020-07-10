package com.company.model;

import java.sql.Timestamp;

public class StudentAward {
    private int userId;
    private int awardId;
    private int price;
    private Timestamp boughAt;

    public StudentAward(int userId, int awardId, int price, Timestamp boughAt) {
        this.userId = userId;
        this.awardId = awardId;
        this.price = price;
        this.boughAt = boughAt;
    }

    public int getUserId() {
        return userId;
    }

    public StudentAward setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getAwardId() {
        return awardId;
    }

    public StudentAward setAwardId(int awardId) {
        this.awardId = awardId;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public StudentAward setPrice(int price) {
        this.price = price;
        return this;
    }

    public Timestamp getBoughAt() {
        return boughAt;
    }

    public StudentAward setBoughAt(Timestamp boughAt) {
        this.boughAt = boughAt;
        return this;
    }
}
