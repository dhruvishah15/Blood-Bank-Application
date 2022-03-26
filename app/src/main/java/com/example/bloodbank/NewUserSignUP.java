package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class NewUserSignUP extends AppCompatActivity {
    private EditText email;
    private EditText newpassword;
    private TextView oldacc;
    public FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private ImageView profile;
    private String mail,pass;
    private Button signup;
    StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagepath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null)
        {
            imagepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_sign_up);

        setupUI();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");//application/*    audio/*
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });


        progressDialog = new ProgressDialog(NewUserSignUP.this);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    String user_email = email.getText().toString().trim();
                    String user_pass = newpassword.getText().toString().trim();
                    progressDialog.setMessage("Loading");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendEmailVerification();
                                sendUserPic();

                                //Toast.makeText(NewUserSignUP.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(NewUserSignUP.this,MainActivity.class));
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(NewUserSignUP.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    //Intent newuser = new Intent(NewUserSignUP.this,Second.class);
                    //startActivity(newuser);
                }
                //signup.setEnabled(validate());

            }
        });
        oldacc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent old = new Intent(NewUserSignUP.this,MainActivity.class);
                startActivity(old);
            }
        });

    }

    private boolean validate()
    {
        boolean result;
        result = false;

        mail = email.getText().toString();
        pass = newpassword.getText().toString();
        if(pass.isEmpty() || mail.isEmpty() || imagepath == null)
        {
            Toast.makeText(this,"Please Enter all the details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }

    private void sendEmailVerification()
    {
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                       //sendUserData();
                        Toast.makeText(NewUserSignUP.this,"Successfully Registered!Verification for Email Sent!",Toast.LENGTH_SHORT).show();
                        OneTimeAcess();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(NewUserSignUP.this,MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(NewUserSignUP.this,"Verification for Email Not Sent!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
       }
    }
    public void sendUserPic()
    {
        StorageReference imageref = storageReference.child(firebaseAuth.getUid()).child("Profile pic");
        UploadTask uploadTask = imageref.putFile(imagepath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewUserSignUP.this,"Upload Failed",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(NewUserSignUP.this,"Successfull",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setupUI()
    {
        profile = (ImageView)findViewById(R.id.imageViewProfile);
        email = (EditText)findViewById(R.id.etmail);
        newpassword = (EditText)findViewById(R.id.etnewpass);
        signup = (Button) findViewById(R.id.btSign);
        oldacc = (TextView) findViewById(R.id.etoldAcc);
    }
    public void OneTimeAcess()
    {
        SharedPreferences preferences = getSharedPreferences("PREFRENCE",MODE_PRIVATE);
        MainActivity.FirstTime = preferences.getString("FirstTimeInstall","");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FirstTimeInstall","");
        editor.apply();
    }
}