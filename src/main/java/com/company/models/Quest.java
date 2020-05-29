package com.company.models;

public class Quest {
    private int id;
    private String title;
    private String description;
    private int price;
    private String imageSrc;
    private int mentorId;

    public Quest(int id, String title, String description, int price, String imageSrc, int mentorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.mentorId = mentorId;
    }

    public Quest(String title, String description, int price, String imageSrc, int mentorId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
        this.mentorId = mentorId;
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
                " Mentor_id: " + mentorId;
    }
}
