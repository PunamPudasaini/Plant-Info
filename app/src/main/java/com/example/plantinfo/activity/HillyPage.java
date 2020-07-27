package com.example.plantinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plantinfo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HillyPage extends AppCompatActivity {
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilly_page);
        floatingActionButton = findViewById(R.id.floatbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent());
            }
        });
    }
}