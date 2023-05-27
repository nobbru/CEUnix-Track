package com.bawkertech.ceunixtack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.bawkertech.ceunixtack.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


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



        });



    }
}