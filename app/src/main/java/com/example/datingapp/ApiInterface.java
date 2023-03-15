package com.example.datingapp;

import com.example.datingapp.ViewModels.albumModel;
import com.example.datingapp.ViewModels.loginModel.LoginModel;
import com.example.datingapp.ViewModels.users.Data;
import com.example.datingapp.ViewModels.users.ModelUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("posts")
    Call<List<albumModel>> getPosts();

    @GET("photos")
    Call<List<albumModel>> getPhotos();

    @GET("presignup")
    Call<LoginModel> getLogin(
            @Field("phone") String phone
    );

   @FormUrlEncoded
    @POST("auth")
    Call<ModelUser>  getData(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("timezone") String timezone,
            @Field("device_id") String device_id,
            @Field("device_type") String device_type,
            @Field("image") String image
    );

}
