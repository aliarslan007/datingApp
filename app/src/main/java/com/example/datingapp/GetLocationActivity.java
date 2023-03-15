package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GetLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
    Fragment fragment=new MapFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.frameForMap, fragment).commit();
    }


    public void onClickNextInNLocation(View v){

        Intent i = new Intent(this,HomeNavigation.class);
        startActivity(i);
        finishAffinity();
    }
    @Override
    public void onBackPressed() {

        this.finish();
    }
}