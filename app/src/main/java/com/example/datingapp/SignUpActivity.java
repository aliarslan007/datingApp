package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.datingapp.ViewModels.users.Data;
import com.example.datingapp.ViewModels.users.ModelUser;
import com.example.datingapp.post_models.PostAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText selectDate;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String name, email, passwrod, phone;
    String timezone="Lahore ",device_id="334", device_type="android", image="noimg.png";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextInputEditText nameETextLayout, emailETextLayout,passwordETextLayout,phoneETextLayout;
    TextInputLayout nameLayout,emailLayout,passwordLayout, phoneLayout;

    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        selectDate= findViewById(R.id.dateEditText);
        nameLayout= findViewById(R.id.nameLayout);
        emailLayout= findViewById(R.id.emailLayout);
        passwordLayout= findViewById(R.id.passwordLayout);
        emailETextLayout=findViewById(R.id.emailETextLayout);
        passwordETextLayout=findViewById(R.id.passwordETextLayout);
        phoneLayout= findViewById(R.id.phoneLayout);
        phoneETextLayout= findViewById(R.id.phoneETextLayout);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


    }


    public void datePicker(View v){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void clickListenerOnCreateSignup(View v){
        performAuth();


    }

    private void performAuth() {
        Log.e("check","top of performAuth");
        name= nameLayout.getEditText().getText().toString().trim();
        passwrod= passwordLayout.getEditText().getText().toString().trim();
        email= emailLayout.getEditText().getText().toString().trim();
        phone= phoneLayout.getEditText().getText().toString().trim();
        Log.e("check","before first if");

        if (!email.matches(emailPattern) || email.isEmpty()){

            emailETextLayout.setError("Email is not correct");
            Log.e("check","in email validation");

        }else if (passwrod.length()<8 || passwrod.isEmpty()){
            Log.e("check","in password first validation");

            passwordETextLayout.setError("password is not correct");
            Log.e("check","in password validation");

        }
        else {
            Log.e("check","in auth validation");
            signupWihFirebase();
//            signupWihAPIServer();

        }
    }

    private void signupWihAPIServer() {

        PostAuth postAuth = new PostAuth(image, device_id, phone, timezone, name, device_type);
        Log.d("SIGNUPRES", "signupWihAPIServer: " + postAuth);

        RetrofitInstanceClass.getInstance().apiInterface.getData(postAuth).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "SignUp is not successful \n Error: "+response.code(), Toast.LENGTH_SHORT).show();
                return;
                }

                if (response.code()==200){
                    //                Data jsonObject=response.body();
                    Log.e("check", " response is :"+response);
                    ModelUser data=response.body();
                    Log.d("SIGNUPRESPONSE", "onResponse: " + response.body().getData());
//                String n,p;
//                n=data.getData().getName();
//                p=data.getData().getPhone();
                    Toast.makeText(SignUpActivity.this, response.body().getMessage() + data.getData().getName(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SignUpActivity.this, response.message().toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {

            }
        });

    }

    private void signupWihFirebase() {
        mAuth.createUserWithEmailAndPassword(email, passwrod).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Toast.makeText(SignUpActivity.this, "Registration is successful ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignUpActivity.this, GenderActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            } else {
                Toast.makeText(SignUpActivity.this, "Registration is not successful, Error is: " + task.getException(), Toast.LENGTH_SHORT).show();

            }
        }
    });


    }

    @Override
    public void onBackPressed() {

        this.finish();
    }

}