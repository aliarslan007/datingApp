package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.datingapp.ViewModels.PreSignup;
import com.example.datingapp.ViewModels.loginModel.LoginModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class LogInActivity extends AppCompatActivity {

    String url="https://mocki.io/v1/1988d662-6fd7-486f-860b-fe9ae4b82276";
    String email="",password="",emailText="",passwordText="",s;
    String phone="+923137909583";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextInputLayout  emailView, passwordView;
    TextInputEditText emailEditText, passEditText;
    ImageView imageView;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
//
        login= findViewById(R.id.loginButton);
        emailView=findViewById(R.id.loginEmailView);
        passwordView=findViewById(R.id.loginPasswordView);
        emailEditText=findViewById(R.id.loginEmailId);
        passEditText=findViewById(R.id.loginPasswordId);
        imageView=findViewById(R.id.imageViewInLogin);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
    }

    public void OnLoginClick(View view){
       emailText=emailView.getEditText().getText().toString().trim();
       passwordText=passwordView.getEditText().getText().toString().trim();

//        authPerfromWithAPI();
        authPerformWithFirebase();
    }

    private void authPerformWithFirebase() {
        parsData();
          if (!emailText.matches(emailPattern)) {
            emailEditText.setError("Enter correct email ");
        }
        else if(emailText.isEmpty()||passwordText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();

        } else if(passwordText.length()<8){
            passEditText.setError("Enter correct password ");
        }
        else {
            mAuth.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LogInActivity.this, "Login successful ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LogInActivity.this, HomeNavigation.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(LogInActivity.this, "Login Unsuccessful \n Error is "+task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
    private void authPerfromWithAPI() {
        if(emailText.equals("")||passwordText.equals("")) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();

        } else if (!emailText.matches(emailPattern)) {
            emailEditText.setError("Enter correct email ");
        } else {
            connectAliAPI();
            connectThroughVolleyResult();
        }
        connectAliAPI();


    }

    private void connectThroughVolleyResult() {
        if (emailText.equals(email) && password.equals(passwordText)) {
            Intent i = new Intent(this, HomeNavigation.class);
            startActivity(i);
            finishAffinity();
        } else {
            Toast.makeText(this, "Please enter correct email and Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void connectAliAPI() {
//          Log.e("check", " in login is api");

        PreSignup preSignupObject= new PreSignup();
        preSignupObject.setPhone(emailText);
        RetrofitInstanceClass.getInstance().apiInterface.presignup(preSignupObject).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, retrofit2.Response<LoginModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LogInActivity.this, "Login is not successful \n Error: "+response.code(), Toast.LENGTH_SHORT).show();
                   Log.e("check","response is \n "+response.code());
                    return;
                }
//                Data jsonObject=response.body();
                Log.e("check", " response in login is :"+response.body());
                if(response.code()==200){
                    if(response.body().getData().isIsUser()==true){
                        Intent i = new Intent(LogInActivity.this, HomeNavigation.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(LogInActivity.this, "Login is not successful, \n Account is not registered " , Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(LogInActivity.this, "Login is not successful, Error is: " + response.message().toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LogInActivity.this, "Login is not successful \n Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("check","response message is is \n "+t.getMessage());


            }
        });
    }

    public void parsData(){
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject!=null){

                        email= jsonObject.getString("email");
                        password=jsonObject.getString("password");

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
//    @Override
//    public void onBackPressed() {
//
//        this.finish();
//    }
}