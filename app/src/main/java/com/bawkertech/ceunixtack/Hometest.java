package com.bawkertech.ceunixtack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.bawkertech.ceunixtack.databinding.ActivityHometestBinding;
import com.bawkertech.ceunixtack.databinding.ActivityLoginBinding;

public class Hometest extends AppCompatActivity {

    private ActivityHometestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHometestBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        Intent  intent = getIntent();

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        binding.textView3.setText(username);
        binding.textView4.setText(password);
    }
}