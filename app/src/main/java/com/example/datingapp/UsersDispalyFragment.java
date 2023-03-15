package com.example.datingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.datingapp.ViewModels.albumModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersDispalyFragment extends Fragment {

    public UsersDispalyFragment() {

    }


    TextView users_result;
     ProgressBar progressBar;
//    public  static String apiUrl="https://jsonplaceholder.typicode.com";

    public  static String apiUrl="https://naseejprod.azurewebsites.net/api/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_users_dispaly, container, false);
//        progressBar= getView().findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("api", "in view created");
        users_result=view.findViewById(R.id.users_result);
        progressBar=view.findViewById(R.id.progressBar);

        RetrofitInstanceClass.getInstance().apiInterface.getPosts().enqueue(new Callback<List<albumModel>>() {
            @Override
            public void onResponse(Call<List<albumModel>> call, Response<List<albumModel>> response) {
            if(!response.isSuccessful()){
                users_result.setText("Code : "+response.code());
                return;
                }
               List<albumModel> album=response.body();
               //
            for(albumModel albummodel: album) {

                String content = "";
                content += "ID: " + albummodel.getId() + "\n";
                content += "User ID: " + albummodel.getUserId() + "\n";
                content += "Title: " + albummodel.getTitle() + "\n";
                content += "Text: " + albummodel.getText() + "\n\n";
                users_result.append(content);
            }
            progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<albumModel>> call, Throwable t) {
                Log.e("api", "api call fail in Users Display "+t.getLocalizedMessage());
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}