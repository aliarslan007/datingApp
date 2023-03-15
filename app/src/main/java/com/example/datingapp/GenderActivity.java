package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
    }



    public void onClickNext(View view) {
        Intent i = new Intent(this, PictureSelectionActivity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {

        this.finish();
    }

}