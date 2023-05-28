package com.bawkertech.ceunixtack.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import android.os.AsyncTask;

import com.bawkertech.ceunixtack.databases.MissingPersonDatabaseHelper;
import com.bawkertech.ceunixtack.models.MissingPerson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MissingPersonService extends Service {
    private static final String API_URL = "http://192.168.8.176/api/missing_persons";
    private static final long INTERVAL = 5 * 60 * 1000; // 5 minutes
    private static final int REQUEST_CODE = 123;

    private List<MissingPerson> missingPersons;
    private boolean isServiceRunning;
    private MissingPersonDatabaseHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        missingPersons = new ArrayList<>();
        isServiceRunning = false;
        dbHelper = new MissingPersonDatabaseHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isServiceRunning) {
            isServiceRunning = true;
            new FetchMissingPersonsTask().execute();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
    }

    private void scheduleAlarm() {
        Intent alarmIntent = new Intent(this, MissingPersonAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MINUTE, 5);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL, pendingIntent);
        }
    }

    private class FetchMissingPersonsTask extends AsyncTask<Void, Void, List<MissingPerson>> {
        @Override
        protected List<MissingPerson> doInBackground(Void... voids) {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse the JSON response and populate the missingPersons list
                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                        String lastKnownLocation = jsonObject.getString("last_known_location");
                        String dateOfDisappearance = jsonObject.getString("date_of_disappearance");
                        String age = jsonObject.getString("age");
                        String gender = jsonObject.getString("gender");
                        String image = jsonObject.getString("image");

                        MissingPerson missingPerson = new MissingPerson( name, age,  gender, image, description, lastKnownLocation, dateOfDisappearance);
                        missingPersons.add(missingPerson);
                    }

                    // Save the missing persons to the local database
                    dbHelper.saveMissingPersons(missingPersons);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return missingPersons;
        }

        @Override
        protected void onPostExecute(List<MissingPerson> result) {
//            Intent intent = new Intent("missing_persons_data");
//            intent.putParcelableArrayListExtra("missing_persons", new ArrayList<>(result));
//            sendBroadcast(intent);
            scheduleAlarm(); // Schedule the next alarm

            // Stop the service after retrieving the data
            stopSelf();
        }
    }

}
