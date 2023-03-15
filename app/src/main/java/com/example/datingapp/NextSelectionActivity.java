package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NextSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_selection);
    }

    public void onClickNextInNextSelect(View v){
        Intent i = new Intent(this,GetLocationActivity.class );
        startActivity(i);
    }
    public void onClickSkipInNextSelect(View v){
        Intent i = new Intent(this,GetLocationActivity.class );
        startActivity(i);
    }

    public void onClickPic(View view) {


    }
    @Override
    public void onBackPressed() {

        this.finish();
    }
}