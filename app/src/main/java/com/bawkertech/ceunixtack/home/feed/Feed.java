package com.bawkertech.ceunixtack.home.feed;


import android.content.Context;
import android.content.SharedPreferences;

import com.bawkertech.ceunixtack.App;
import com.bawkertech.ceunixtack.R;
import com.bawkertech.ceunixtack.models.MissingPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Feed {

    private static final String STORAGE = "feed";

    public static Feed get() {
        return new Feed();
    }

    private SharedPreferences storage;

    private Feed() {
        storage = App.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

    public List<Item> getData() {
        List<Item> itemList = new ArrayList<>();
        System.out.println(App.list.toString());
        System.out.println(App.list.size());

        int i = 1;
        for (MissingPerson person : App.list) {
            itemList.add(new Item(i, person.getName(), person.getDateOfDisappearance(),person.getImage_d()));
            i++;
        }

        return itemList;
    }


    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
