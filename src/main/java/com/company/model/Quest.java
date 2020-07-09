package com.company.model;

public class Quest {
    private int id;
    private String title;
    private String description;
    private int price;
    private String imageSrc;
    private int mentorId;
    private String mentorNameAndSurname;

    public Quest() {

    }

    public Quest(int id, String title, String description, int price, String imageSrc, int mentorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.mentorId = mentorId;
    }

    public Quest(int id, String title, String description, int price, String imageSrc, String mentorNameAndSurname) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.mentorNameAndSurname = mentorNameAndSurname;
    }

    public Quest(String title, String description, int price, String imageSrc, int mentorId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.mentorId = mentorId;
    }

    public Quest setId(int id) {
        this.id = id;
        return this;
    }

    public Quest setTitle(String title) {
        this.title = title;
        return this;
    }

    public Quest setDescription(String description) {
        this.description = description;
        return this;
    }

    public Quest setPrice(int price) {
        this.price = price;
        return this;
    }

    public Quest setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
        return this;
    }

    public Quest setMentorId(int mentorId) {
        this.mentorId = mentorId;
        return this;
    }

    public Quest setMentorNameAndSurname(String mentorNameAndSurname) {
        this.mentorNameAndSurname = mentorNameAndSurname;
        return this;
    }

    public String getMentorNameAndSurname() {
        return mentorNameAndSurname;
    }


    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public int getPrice() {
        return price;
    }


    public String getImageSrc() {
        return imageSrc;
    }


    public int getMentorId() {
        return mentorId;
    }


    public String toString() {
        return "Quest:\n" +
                "Id: " + id +
                " Title: " + title +
                " Description: " + description +
                " Price: " + price +
                " Img_src: " + imageSrc +
                " Mentor_id: " + mentorId;
    }
}
