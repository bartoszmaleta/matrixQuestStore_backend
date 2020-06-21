package com.company.models;

public class Transaction {
    private int transactionId;
    private final String buyer;
    private final String date;
    private final String artifactName;

    public Transaction(String buyer, String date, String artifactName) {
        this.buyer = buyer;
        this.date = date;
        this.artifactName = artifactName;
    }
    public Transaction(int transactionId, String buyer, String date, String artifactName) {
        this(buyer, date, artifactName);
        this.transactionId = transactionId;
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

    public String getDate() {
        return date;
    }

    public String getArtifactName() {
        return artifactName;
    }
}
