package com.company.models;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private final int buyerId;
    private final String buyer;
    private final Timestamp date;
    private final int artifactId;
    private final String artifactTitle;
    private int price;

    public Transaction(int buyerId, String buyer, Timestamp date, int artifactId, String artifactTitle, int price) {
        this.buyerId = buyerId;
        this.buyer = buyer;
        this.date = date;
        this.artifactId = artifactId;
        this.artifactTitle = artifactTitle;
        this.price = price;
    }
    public Transaction(int transactionId, int buyerId, String buyer, Timestamp date, int artifactId, String artifactTitle, int price) {
        this(buyerId, buyer, date, artifactId, artifactTitle, price);
        this.transactionId = transactionId;
    }

    public int getPrice() {
        return price;
    }

    public Transaction setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getArtifactId() {
        return artifactId;
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

    public String getArtifactTitle() {
        return artifactTitle;
    }
}
