package com.bawkertech.ceunixtack;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bawkertech.ceunixtack.models.MissingPerson;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class App extends Application {


    public static List<MissingPerson> list = new ArrayList<>();

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DiscreteScrollViewOptions.init(this);
    }

    public static Drawable fetchImage(String type, int id){
        final Drawable[] mydrawable = {null};
// Create an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient();

// Create a request with the image URL
        Request request = new Request.Builder()
                .url("https://randomuser.me/api/portraits/"+type+"/"+id+".jpg") // Replace with your image URL
                .build();

// Asynchronously execute the request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Image data is retrieved successfully
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        // Convert the response body to a Drawable
                        InputStream inputStream = responseBody.byteStream();
                        Drawable drawable = Drawable.createFromStream(inputStream, "image_name");
                        // Use the drawable as needed
                        mydrawable[0] =  drawable;
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
            }
        });

        return mydrawable[0];

    }


    public static void setImages(){
        if (list.size() < 1){
            seedPeople(12);
        }
        int i = 1;
        for(MissingPerson missingPerson: list){
            if (missingPerson.getGender().toLowerCase().equals("male"))
            {
                missingPerson.setImage_d(fetchImage("men", i));
            }else {
                missingPerson.setImage_d(fetchImage("women", i));
            }
            i++;
            System.out.println(missingPerson);
        }
    }

    private static void seedPeople(int count) {
        List<MissingPerson> missingPersons = new ArrayList<>();
        Random random = new Random();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            MissingPerson missingPerson = new MissingPerson();
            missingPerson.setName(faker.name().fullName());
            missingPerson.setAge(String.valueOf(random.nextInt(80) + 10));
            missingPerson.setGender(random.nextBoolean() ? "Male" : "Female");
            missingPerson.setImage("image_url_" + (i + 1));
            missingPerson.setDescription("Description " + (i + 1));
            missingPerson.setLastKnownLocation("Location " + (i + 1));
            missingPerson.setDateOfDisappearance("2023-01-01");

            missingPersons.add(missingPerson);
//            System.out.println(missingPerson);
            App.list.add(missingPerson);
        }



    }


}
