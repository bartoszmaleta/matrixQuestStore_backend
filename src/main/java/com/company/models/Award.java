package com.company.models;

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

    public void setMentorNameAndSurname(String mentorNameAndSurname) {
        this.mentorNameAndSurname = mentorNameAndSurname;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
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

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public String toString() {
        return "Quest:\n" +
                "Id: " + id +
                " Title: " + title +
                " Description: " + description +
                " Price: " + price +
                " Img_src: " + imageSrc +
                " Data_creation: " + dataCreation +
                " Creator_id: " + mentorId;
    }
}
