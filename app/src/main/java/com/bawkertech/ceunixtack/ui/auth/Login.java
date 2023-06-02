package com.bawkertech.ceunixtack.ui.auth;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.bawkertech.ceunixtack.Hometest;
import com.bawkertech.ceunixtack.databinding.ActivityLoginBinding;
import com.bawkertech.ceunixtack.home.Home;
import com.bawkertech.ceunixtack.services.MissingPersonService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(v -> {
                    if (!validateUsername() | !validatePassword()) {
                        return;
                    }

                    LoginTask loginTask = new LoginTask();
                    loginTask.execute();

                }
        );

        binding.signupScreen.setOnClickListener(v -> {
            Intent intentS = new Intent(Login.this, SignUp.class);

            Pair[] pairs = new Pair[7];

            pairs[0] = new Pair<View, String>(binding.logoImage, "logo_image");
            pairs[1] = new Pair<View, String>(binding.logoText, "logo_text");
            pairs[2] = new Pair<View, String>(binding.sloganName, "logo_desc");
            pairs[3] = new Pair<View, String>(binding.username, "username_tran");
            pairs[4] = new Pair<View, String>(binding.password, "password_tran");
            pairs[5] = new Pair<View, String>(binding.loginButton, "button_tran");
            pairs[6] = new Pair<View, String>(binding.signupScreen, "login_signup_tran");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intentS, options.toBundle());
//            this.finish();


        });


    }


    private class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

            RequestBody body = RequestBody.create(json, mediaType); //create(mediaType, json);


            Request request = new Request.Builder()
                    .url("http://192.168.8.176:5000/api/v1/login")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String res = response.body().string();
                System.out.println(res);
                System.out.println(response.code());
                System.out.println(response.isSuccessful());
                System.out.println(response.message());

                if (response.isSuccessful()) {
//                    clearFilds();
//                    JSONArray jsonArray = new JSONArray(response.toString());
                    System.out.println("It work till here");


                    System.out.println("hello test");
                    Intent serviceIntent = new Intent(Login.this, MissingPersonService.class);
                    startService(serviceIntent);


                    //open new stuff

                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("isLogin", true);
                    startActivity(intent);
                    finish();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void clearFilds() {
        binding.username.setEnabled(false);
        binding.password.setEnabled(false);
        binding.username.clearComposingText();
        binding.password.clearComposingText();
    }

    private boolean validateUsername() {
        String username = binding.username.getText().toString();

        if (username.isEmpty()) {
            //show error
            binding.username.setError("Username is required");
            return false;
        } else {
            binding.username.setError(null);
            //binding.username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String password = binding.password.getText().toString();

        if (password.isEmpty()) {
            //show error
            binding.password.setError("Password is required");
            return false;
        } else {
            binding.password.setError(null);
            //binding.password.setErrorEnabled(false);
            return true;
        }

    }

}