package com.bawkertech.ceunixtack;

//import static android.widget.Toast.*;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.bawkertech.ceunixtack.databinding.ActivitySignUpBinding;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//import com.sun.net.httpserver.HttpHandler;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        binding.buttonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, Login.class);

            Pair[] pairs = new Pair[7];

            pairs[0] = new Pair<View, String>(binding.logoImage, "logo_image");
            pairs[1] = new Pair<View, String>(binding.logoText, "logo_text");
            pairs[2] = new Pair<View, String>(binding.sloganName, "logo_desc");
            pairs[3] = new Pair<View, String>(binding.username, "username_tran");
            pairs[4] = new Pair<View, String>(binding.password, "password_tran");
            pairs[5] = new Pair<View, String>(binding.buttonSignup, "button_tran");
            pairs[6] = new Pair<View, String>(binding.buttonLogin, "login_signup_tran");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());
        });

        binding.buttonSignup.setOnClickListener(view -> {
            if (!validateFullName() | !validateEmail() | !validatePhone() | !validateUsername() | !validatePassword()) {
                return;
            }

            SignUpTask signUpTask = new SignUpTask();
            signUpTask.execute();
        });

    }


    private class SignUpTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            String email = binding.email.getText().toString();
            String phone = binding.phone.getText().toString();
            String fullname = binding.fullName.getText().toString();

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");

            String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"email\":\"" + email + "\",\"phone_number\":\"" + phone + "\",\"full_name\":\"" + fullname + "\"}";

            RequestBody body = RequestBody.create(json, mediaType);


            Request request = new Request.Builder()
                    .url("http://192.168.8.176:5000/api/v1/signup")
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String res = response.body().string();
                System.out.println(res);

                if (response.isSuccessful()) {
                    System.out.println("hello test");
                    makeText(SignUp.this, "Account created successfully", LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Login.class);

                    Pair[] pairs = new Pair[7];

                    pairs[0] = new Pair<View, String>(binding.logoImage, "logo_image");
                    pairs[1] = new Pair<View, String>(binding.logoText, "logo_text");
                    pairs[2] = new Pair<View, String>(binding.sloganName, "logo_desc");
                    pairs[3] = new Pair<View, String>(binding.username, "username_tran");
                    pairs[4] = new Pair<View, String>(binding.password, "password_tran");
                    pairs[5] = new Pair<View, String>(binding.buttonSignup, "button_tran");
                    pairs[6] = new Pair<View, String>(binding.buttonLogin, "login_signup_tran");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                    startActivity(intent, options.toBundle());




//                    SignUp.this.finish();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);
            makeText(SignUp.this, "Registration successful!", LENGTH_SHORT).show();

            finish(); // Optionally finish the SignUp activity after successful registration
//            makeText(SignUp.this, "Registration failed!", LENGTH_SHORT).show();
        }
    }

    private boolean validateFullName() {
        String val = binding.fullName.getText().toString().trim();

        if (val.isEmpty()) {
            binding.fullName.setError("Full Name is required");
            return false;
        } else if (val.length() > 30) {
            binding.fullName.setError("Full Name must be less than 30 characters");
            return false;
        } else {
            binding.fullName.setError(null);
//            binding.fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        String val = binding.phone.getText().toString().trim();

        if (val.isEmpty()) {
            binding.phone.setError("Phone number is required");
            return false;
        } else if (val.length() > 30) {
            binding.phone.setError("Phone number must be less than 30 characters");
            return false;
        } else {
            binding.phone.setError(null);
//            binding.phone.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateEmail() {
        String val = binding.email.getText().toString().trim();
        String checkemail = "\\A\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\\z";

        if (val.isEmpty()) {
            binding.email.setError("Email is required");
            return false;
        } else if (!val.matches(checkemail)) {
            binding.email.setError("Invalid email");
            return false;
        } else {
            binding.email.setError(null);
//            binding.email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = binding.username.getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            binding.username.setError("Username is required");
            return false;
        } else if (val.length() > 20) {
            binding.username.setError("Username must be less than 20 characters");
            return false;
        } else if (val.matches(checkspaces)) {
            binding.username.setError("Username must not contain spaces");
            return false;
        } else {
            binding.username.setError(null);
//            binding.username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = binding.password.getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +  //at least one digit
                "(?=.*[a-z])" +  //at least one lower case
                "(?=.*[A-Z])" +  //at least one upper case
                "(?=.*[@#$%^&+=])" + //at least one special character
                "(?=\\S+$)" +      //no white spaces
                "(?=.{8,20})" +    //at least 8 characters
                "(?=.*[a-zA-Z])"; //any letter


        if (val.isEmpty()) {
            binding.password.setError("Password is required");
            return false;
        } else if (val.length() > 20) {
            binding.password.setError("Password must be less than 20 characters");
            return false;
        } else if (!val.matches(checkPassword)) {
            binding.password.setError("Password must contain at least 8 characters");
            return false;
        } else {
            binding.password.setError(null);
//            binding.password.setErrorEnabled(false);
            return true;
        }
    }

}