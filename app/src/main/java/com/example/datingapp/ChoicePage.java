package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoicePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_page);
    }

    public void onClickCreateAccountBtn(View v){
    Intent i = new Intent(this, SignUpActivity.class);
    startActivity(i);

    }
    public void onClickLoginBtn(View v){
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        this.finish();
    }
}