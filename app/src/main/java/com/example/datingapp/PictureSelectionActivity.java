package com.example.datingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class PictureSelectionActivity extends AppCompatActivity {

    private final int Gallery_Request=1000;
    int id=0;
    ImageView picUploadId1,picUploadId2,picUploadId3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selection);
        picUploadId1= findViewById(R.id.picUploadId1);
        picUploadId2= findViewById(R.id.picUploadId2);
        picUploadId3= findViewById(R.id.picUploadId3);
    }

    public void onClickPicUpload(View v){
        if((v.getId())==(picUploadId1.getId())){
//        Log.d("ali", "id of v.getID "+(picUploadId1.getId()));
        id=1;
        }
        else if ((v.getId())==(picUploadId2.getId())){
            id=2;
        }
        else{
            id=3;
        }
        Intent iGalley= new Intent(Intent.ACTION_PICK);
        iGalley.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGalley,Gallery_Request);
    }

    public void onClickNextInPicSelect(View v){
        Intent i = new Intent(this,NextSelectionActivity.class );
        startActivity(i);
    }
    public void onClickSkipInPicSelect(View v){
        Intent i = new Intent(this,NextSelectionActivity.class );
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){

            if(requestCode==Gallery_Request){
            if (id==1){
                picUploadId1.setImageURI(data.getData());
            }
            else if(id==2){
                picUploadId2.setImageURI(data.getData());
            } else{

                picUploadId3.setImageURI(data.getData());
            }

            }
        }
    }// onActivityResult ends here
    @Override
    public void onBackPressed() {

        this.finish();
    }

}