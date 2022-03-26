package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Profile extends AppCompatActivity {

    private ImageView profilepic;
    private TextView ages,genders,phones,bloodgroups,username,dates;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private SwipeRefreshLayout swipeRefreshLayout;
    String name_first,name_last,name_age,name_gender,name_bloodGroup,name_date,name_contact;
    private ImageButton imageButton;
    SharedPreferences sp;
    private DatabaseReference databaseReference;
    private static int PICK_IMAGE = 123;
    Uri imagepath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null)
        {
            imagepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                profilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //private  UserProfile userProfile;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageButton = (ImageButton)findViewById(R.id.ib_edit);
        ages = (TextView)findViewById(R.id.tv_age);
        genders = (TextView)findViewById(R.id.tv_gender);
        phones = (TextView)findViewById(R.id.tv_phone);
        bloodgroups = (TextView)findViewById(R.id.tv_bloodGroup);
        username = (TextView)findViewById(R.id.tv_profileUsername);
        dates = (TextView)findViewById(R.id.tv_date);
        profilepic = (ImageView)findViewById(R.id.profileView);
//        addphoto = (ImageView)findViewById(R.id.addphoto);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefreshlayout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        storageReference = firebaseStorage.getReference();

        //for retreiving datas into the profile

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                UserProfile userProfile  = snapshot.child("users").child(firebaseAuth.getUid()).getValue(UserProfile.class);
                Log.d("Profile", "onDataChange: name is "+ userProfile.getFirstName());
                genders.setText(userProfile.getGender());
                ages.setText(userProfile.getAge());
                phones.setText(userProfile.getContactNo());
                bloodgroups.setText(userProfile.getBloodGroup());
                dates.setText(userProfile.getDob());
                username.setText(userProfile.getFirstName()+" "+userProfile.getLastName());


                storageReference.child(firebaseAuth.getUid()).child("Profile pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(profilepic);
                    }
                });


//                Toast.makeText(Profile.this, "Hi, I'm "+userProfile.getFirstName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       // Log.d("Profile", "onCreate: "+ userProfile.getFirstName());
        //sendUserPic();
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");//application/*    audio/*
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendUserPic();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

//        addphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendUserPic();
//            }
//        });

        //for editing profile
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,StartProfile.class));
            }
        });
    }

    public void sendUserPic()
    {
        StorageReference imageref = storageReference.child(firebaseAuth.getUid()).child("Profile pic");
        UploadTask uploadTask = imageref.putFile(imagepath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this,"Upload Failed",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Profile.this,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

}