package com.example.datingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datingapp.NearmeRowModel;
import com.example.datingapp.R;

import java.util.ArrayList;

public class NearmeFragmentAdapter extends RecyclerView.Adapter<NearmeFragmentAdapter.ViewHolder>{

    Context context;
    ArrayList<NearmeRowModel> arrRowModel;
    public NearmeFragmentAdapter(Context context, ArrayList<NearmeRowModel> arrRowModel){
        this.context=context;
        this.arrRowModel=arrRowModel;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.nearme_row,parent,false);
        NearmeFragmentAdapter.ViewHolder viewHolder=new NearmeFragmentAdapter.ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull NearmeFragmentAdapter.ViewHolder holder, int position) {
        holder.menImageId1.setImageResource(arrRowModel.get(position).menImageId1);
        holder.menImageId2.setImageResource(arrRowModel.get(position).menImageId2);
        holder.menImageId3.setImageResource(arrRowModel.get(position).menImageId3);
        holder.menImageId4.setImageResource(arrRowModel.get(position).menImageId4);
    }



    @Override
    public int getItemCount() {
        return arrRowModel.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView menImageId1, menImageId2,menImageId3,menImageId4;
        public ViewHolder(View itemView) {
            super(itemView);
            menImageId1=itemView.findViewById(R.id.menImageId1);
            menImageId2=itemView.findViewById(R.id.menImageId2);
            menImageId3=itemView.findViewById(R.id.menImageId3);
            menImageId4=itemView.findViewById(R.id.menImageId4);


        }
    }
}



