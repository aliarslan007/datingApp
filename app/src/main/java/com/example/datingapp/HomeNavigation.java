package com.example.datingapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeNavigation extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    TextView toolbar_title;
    Toolbar toolbar;
    Fragment fragmentNews=new NearmeFragment();
    Fragment userDisplayFragment= new UsersDispalyFragment();
    Fragment NearmeFragment= new NearmeFragment();
    FragmentManager fragmentManager= getSupportFragmentManager();

    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_home_navigation);
        toolbar=findViewById(R.id.toolbarMain);
        toolbar_title=findViewById(R.id.toolbar_title);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        setSupportActionBar(toolbar);
        mToggle=new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView= findViewById(R.id.navigationViewId);
        mToggle.syncState();


        fragmentTransaction.replace(R.id.mainframe, fragmentNews);
        toolbar_title.setText("Near Me");
        fragmentTransaction.commit();


    }

    public void onClickUserDispalyLayout (View v){
        FragmentManager fragmentManager2= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction2=fragmentManager2.beginTransaction();
        fragmentTransaction2.replace(R.id.mainframe, userDisplayFragment);
        toolbar_title.setText("Users From Retrofit");
        fragmentTransaction2.commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);

    }
    public void onClickNearMeLayout (View v){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, NearmeFragment);
        toolbar_title.setText("Near Me");
        fragmentTransaction.commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);

    }
    public void onClickLogoutLayout (View v){
        Log.e("check", "in logout function");
        Intent i = new Intent(HomeNavigation.this, LogInActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }



    @Override
    public void onBackPressed() {

//        this.finish();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();

        }
    }
}