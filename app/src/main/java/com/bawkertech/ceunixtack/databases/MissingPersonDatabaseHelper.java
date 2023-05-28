package com.bawkertech.ceunixtack.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bawkertech.ceunixtack.models.MissingPerson;

import java.util.List;

public class MissingPersonDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "missing_persons.db";
    private static final int DATABASE_VERSION = 1;

    public MissingPersonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table to store missing persons
        db.execSQL("CREATE TABLE missing_persons (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "last_known_location TEXT," +
                "date_of_disappearance TEXT," +
                "age INTEGER," +
                "gender TEXT," +
                "image_url TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public void saveMissingPersons(List<MissingPerson> missingPersons) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("missing_persons", null, null); // Clear existing data

        for (MissingPerson missingPerson : missingPersons) {
            ContentValues values = new ContentValues();
            values.put("name", missingPerson.getName());
            values.put("description", missingPerson.getDescription());
            values.put("last_known_location", missingPerson.getLastKnownLocation());
            values.put("date_of_disappearance", missingPerson.getDateOfDisappearance());
            db.insert("missing_persons", null, values);
        }
    }
}