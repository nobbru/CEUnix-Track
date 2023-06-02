package com.bawkertech.ceunixtack;

import androidx.annotation.NonNull;


public class DonationOption {

    public final String description;
    public final String paltform;

    public DonationOption(String paltform, String description) {
        this.paltform = paltform;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "DonationOption{" +
                "description='" + description + '\'' +
                ", paltform='" + paltform + '\'' +
                '}';
    }
}