package com.bawkertech.ceunixtack.home.feed;


import android.graphics.drawable.Drawable;

public class Item {

    private final int id;
    private final String name;
    private final String missingDate;
    private final Drawable image;

    public Item(int id, String name, String missingDate, Drawable image) {
        this.id = id;
        this.name = name;
        this.missingDate = missingDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMissingDate() {
        return missingDate;
    }

    public Drawable getImage() {
        return image;
    }
}