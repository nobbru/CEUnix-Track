package com.bawkertech.ceunixtack.models;

import java.io.Serializable;

public class MissingPerson implements Serializable {
    private String name;
    private String age;
    private String gender;
    private String image;
    private String description;
    private String lastKnownLocation;
    private String dateOfDisappearance;

    public MissingPerson(String name, String age, String gender, String image, String description, String lastKnownLocation, String dateOfDisappearance) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.image = image;
        this.description = description;
        this.lastKnownLocation = lastKnownLocation;
        this.dateOfDisappearance = dateOfDisappearance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(String lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public String getDateOfDisappearance() {
        return dateOfDisappearance;
    }

    public void setDateOfDisappearance(String dateOfDisappearance) {
        this.dateOfDisappearance = dateOfDisappearance;
    }
}
