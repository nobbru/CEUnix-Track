package com.bawkertech.ceunixtack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.bawkertech.ceunixtack.databinding.ActivityLoginBinding;
import com.bawkertech.ceunixtack.databinding.ActivitySignUpBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        binding.buttonLogin.setOnClickListener(view -> {
            this.finish();
        });

        binding.buttonSignup.setOnClickListener(view -> {

            //TODO: add code to go to "http://192.168.8.176:5000/signup" and send the username and password

            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            String  email = binding.email.getText().toString();
            String  phone = binding.phone.getText().toString();
            String fullname = binding.fullName.getText().toString();


            System.out.println("Stp 1");
            try {
                URL url = new URL("http://192.168.8.176:5000/signup");

                HttpURLConnection  connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-Type", "application/json; utf-8");

                connection.setRequestProperty("Accept", "application/json");


                connection.setDoOutput(true);

                System.out.println("Stp 2");

                String payload = "{\"username\":\""+username+"\",\"password\":\""+password+"\",\"email\":\""+email+"\",\"phone\":\""+phone+"\",\"fullname\":\""+fullname+"\"}";

                OutputStream outputStream = connection.getOutputStream();

                outputStream.write(payload.getBytes());

                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();

                System.out.println(responseCode);

                BufferedReader  in = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                System.out.println("Stp 3");
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("response: "+response.toString());


//                if(responseCode == 200){
//                    this.finish();
//                }

                System.out.println("Stp 4");

//
                connection.disconnect();

            }catch (IOException e){
                e.printStackTrace();
            }


        });

    }


}