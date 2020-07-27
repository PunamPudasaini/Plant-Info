package com.example.plantinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.plantinfo.R;
import com.example.plantinfo.adapter.PlantAdapter;
import com.example.plantinfo.dto.Plant;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    RecyclerView recyclerView;
    PlantAdapter plantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plantAdapter = new PlantAdapter(this, getmylist());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(plantAdapter);

    }

    private ArrayList<Plant> getmylist() {
        ArrayList<Plant> arrayList = new ArrayList<>();

        Plant plant = new Plant();
        plant.setTitle("Himalayan");
        plant.setImg(R.mipmap.himalaya);
        arrayList.add(plant);


        plant = new Plant();
        plant.setTitle("Hilly");
        plant.setImg(R.mipmap.hill);
        arrayList.add(plant);

       plant = new Plant();
        plant.setTitle("Terai");
        plant.setImg(R.mipmap.terai);
        arrayList.add(plant);

        return arrayList;

    }
}