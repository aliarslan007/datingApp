package com.example.datingapp;

import com.example.datingapp.ViewModels.PreSignup;
import com.example.datingapp.ViewModels.albumModel;
import com.example.datingapp.ViewModels.loginModel.LoginModel;
import com.example.datingapp.ViewModels.users.Data;
import com.example.datingapp.ViewModels.users.ModelUser;
import com.example.datingapp.post_models.PostAuth;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("posts")
    Call<List<albumModel>> getPosts();

    @GET("photos")
    Call<List<albumModel>> getPhotos();

    @POST("presignup")
    Call<LoginModel> presignup(
            @Body PreSignup body
            );



//  requests with body does not need @FormUrlEncoded

    @POST("auth")
    Call<ModelUser>  getData(
            @Body PostAuth body
    );

}
