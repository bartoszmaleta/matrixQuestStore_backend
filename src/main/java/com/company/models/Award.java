package com.company.models;

import java.sql.Timestamp;

public class Award {
    private int id;
    private String title;
    private String description;
    private int price;
    private String imageSrc;
    private Timestamp dataCreation;
    private int creatorId;

    public Award(int id, String title, String description, int price, String imageSrc, Timestamp dataCreation, int creatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.dataCreation = dataCreation;
        this.creatorId = creatorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Timestamp getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(Timestamp dataCreation) {
        this.dataCreation = dataCreation;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
