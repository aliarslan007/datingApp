package com.example.datingapp;

import static com.example.datingapp.UsersDispalyFragment.apiUrl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceClass {

    public static RetrofitInstanceClass instance;
    ApiInterface apiInterface;
    RetrofitInstanceClass ( ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       apiInterface= retrofit.create(ApiInterface.class);
    }


    public static RetrofitInstanceClass getInstance(){
        if (instance==null){
            instance=new RetrofitInstanceClass();
            return instance;
        }
        return instance;
    }

}
