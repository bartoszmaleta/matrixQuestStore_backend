package com.company.model;

import com.company.model.user.User;

import java.sql.Timestamp;

public class Award {
    private int id;
    private String title;
    private String description;
    private int price;
    private String imageSrc;
    private Timestamp dataCreation;
    private int mentorId;
    private String mentor;
    private String mentorNameAndSurname;
    private String ownerCredentials;



    public Award() {
    }

    public String getOwnerCredentials() {
        return ownerCredentials;
    }

    public Award setOwnerCredentials(String ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
        return this;
    }

    public Award(int id, String ownerCredentials, String title, String description, int price, String imageSrc, int mentorId, Timestamp dataCreation) {
        this.id = id;
        this.ownerCredentials = ownerCredentials;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.mentorId = mentorId;
        this.dataCreation = dataCreation;
    }

    public Award(String title, String description, int price, String imageSrc, Timestamp dataCreation, int mentorId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.dataCreation = dataCreation;
        this.mentorId = mentorId;
    }

    public Award(int id, String title, String description, int price, String imageSrc, Timestamp dataCreation, int mentorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.dataCreation = dataCreation;
        this.mentorId = mentorId;
    }

//    public Award(int id, String title, String description, int price, String imageSrc, Timestamp dataCreation, String mentor) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.price = price;
//        this.imageSrc = imageSrc;
//        this.dataCreation = dataCreation;
//        this.mentor = mentor;
//    }

    // NEW
    public Award(int id, String title, String description, int price, String imageSrc, Timestamp dataCreation, String mentorNameAndSurname) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.dataCreation = dataCreation;
        this.mentorNameAndSurname = mentorNameAndSurname;
    }

    public String getMentorNameAndSurname() {
        return mentorNameAndSurname;
    }

    public Award setMentorNameAndSurname(String mentorNameAndSurname) {
        this.mentorNameAndSurname = mentorNameAndSurname;
        return this;
    }

    public String getMentor() {
        return mentor;
    }

    public Award setMentor(String mentor) {
        this.mentor = mentor;
        return this;
    }

    public int getId() {
        return id;
    }

    public Award setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Award setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Award setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Award setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public Award setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
        return this;
    }

    public Timestamp getDataCreation() {
        return dataCreation;
    }

    public Award setDataCreation(Timestamp dataCreation) {
        this.dataCreation = dataCreation;
        return this;
    }

    public int getMentorId() {
        return mentorId;
    }

    public Award setMentorId(int mentorId) {
        this.mentorId = mentorId;
        return this;
    }

    public String toString() {
        return "Quest:\n" +
//                "Id: " + id +
                " Title: " + title +
                " Description: " + description +
                " Price: " + price +
                " Img_src: " + imageSrc +
                " Data_creation: " + dataCreation +
                " Creator_id: " + mentorId;
    }
}
