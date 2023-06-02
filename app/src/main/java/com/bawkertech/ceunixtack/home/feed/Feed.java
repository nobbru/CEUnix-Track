package com.bawkertech.ceunixtack.home.feed;


import android.content.Context;
import android.content.SharedPreferences;

import com.bawkertech.ceunixtack.App;
import com.bawkertech.ceunixtack.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 07.03.2017.
 */

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
        return Arrays.asList(
                new Item(1, "John Doe", "27/02/03", R.drawable._576774575wings_love_freesvg_org),
                new Item(2, "John Doe", "27/02/03", R.drawable._576774575wings_love_freesvg_org),
                new Item(3, "John Doe", "27/02/03", R.drawable._576774575wings_love_freesvg_org),
                new Item(4, "John Doe", "27/02/03", R.drawable._576774575wings_love_freesvg_org),
                new Item(5, "John Doe", "27/02/03", R.drawable._576774575wings_love_freesvg_org),
                new Item(6, "John Doe", "27/02/03", R.drawable._576774575wings_love_freesvg_org));
    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}