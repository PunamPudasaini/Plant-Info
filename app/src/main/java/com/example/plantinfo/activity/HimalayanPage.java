package com.example.plantinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plantinfo.R;
import com.example.plantinfo.dto.HimalayanPlant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HimalayanPage extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<HimalayanPlant> options;
    FirebaseRecyclerAdapter<HimalayanPlant, MyViewHolder> adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himalayan_page);
        databaseReference = FirebaseDatabase.getInstance().getReference("Himalayan Plants");
        floatingActionButton = findViewById(R.id.floatbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HimalayanPage.this, AddHimalayaPlant.class));
            }
        });

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadData();
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<HimalayanPlant>().setQuery(databaseReference, HimalayanPlant.class).build();
        adapter = new FirebaseRecyclerAdapter<HimalayanPlant, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull HimalayanPlant model) {
                holder.plantname.setText(model.getPlantName());

            }
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.himalayaplantlist,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
      class MyViewHolder extends RecyclerView.ViewHolder {
        TextView plantname;

          public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        plantname = itemView.findViewById(R.id.plant);
    }
}