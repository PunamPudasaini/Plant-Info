package com.example.plantinfo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantinfo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddHimalayaPlant extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE =101;
    EditText plantname, plantdesp;
    ImageView imageView;
    ProgressBar progressBar;
    Button add;
    Uri ImageUri;
    boolean isImageAdded = false;

    DatabaseReference databaseReference;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_himalaya_plant);

        plantname = findViewById(R.id.plantname);
        plantdesp = findViewById(R.id.plantdesp);
        imageView = findViewById(R.id.imageview);
        progressBar = findViewById(R.id.progressbar);
        add = findViewById(R.id.add);

       databaseReference = FirebaseDatabase.getInstance().getReference("Himalayan Plants");
       storageReference = FirebaseStorage.getInstance().getReference().child("HimalayanPlantsImage");
        progressBar.setVisibility(View.GONE);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName =plantname.getText().toString();
                if (isImageAdded!=false && imageName!=null){
                    UploadImage(imageName);
                }
            }
        });
    }

    private void UploadImage(final String imageName) {
        progressBar.setVisibility(View.VISIBLE);

        final String key = databaseReference.push().getKey();
        storageReference.child(key + ".jpg").putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap =new HashMap();
                        hashMap.put("Plant name", imageName);
                        hashMap.put("imageUrl" , uri.toString());

                        databaseReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddHimalayaPlant.this, "Data Successfully Uploaded", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && data!=null){
            ImageUri =data.getData();
            isImageAdded=true;
            imageView.setImageURI(ImageUri);

        }
    }
}