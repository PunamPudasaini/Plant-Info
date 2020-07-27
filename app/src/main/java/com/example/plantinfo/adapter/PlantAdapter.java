package com.example.plantinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantinfo.R;
import com.example.plantinfo.activity.HillyPage;
import com.example.plantinfo.activity.HimalayanPage;
import com.example.plantinfo.activity.TeraiPage;
import com.example.plantinfo.dto.Plant;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {
    Context context;
    ArrayList<Plant> plantArrayList;

    public PlantAdapter(Context context, ArrayList<Plant> plantArrayList) {
        this.context = context;
        this.plantArrayList = plantArrayList;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlantViewHolder holder, final int position) {

        holder.title.setText(plantArrayList.get(position).getTitle());
        holder.imageView.setImageResource(plantArrayList.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position ==0){
                    Toast.makeText(context, "Clicked" +position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, HimalayanPage.class);
                    context.startActivity(intent);
                }
                else if (position == 1){
                    Toast.makeText(context, "Clicked" +position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, HillyPage.class);
                    context.startActivity(intent);
                }
                else if (position == 2){
                    Toast.makeText(context, "Clicked" +position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, TeraiPage.class);
                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return plantArrayList.size();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
