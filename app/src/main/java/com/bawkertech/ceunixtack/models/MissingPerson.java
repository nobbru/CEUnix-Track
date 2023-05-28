package com.bawkertech.ceunixtack.models;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MissingPerson implements Serializable {
    private String name;
    private String age;
    private String gender;
    private String image;
    private String description;
    private String lastKnownLocation;
    private String dateOfDisappearance;

    public MissingPerson(String name, String age, String gender, String image,  String description, String lastKnownLocation, String dateOfDisappearance) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.image = image;
        this.description = description;
        this.lastKnownLocation = lastKnownLocation;
        this.dateOfDisappearance = dateOfDisappearance;
    }

}
