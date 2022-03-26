package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordAdmin extends AppCompatActivity
{

    private FirebaseAuth firebaseAuth;
    private EditText passEmailreset;
    private Button resetPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp);

        passEmailreset = (EditText)findViewById(R.id.etEmailAdd);
        resetPassword = (Button)findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(ForgotPasswordAdmin.this);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Useremail = passEmailreset.getText().toString().trim();

                if(Useremail.equals("admin123@gmail.com"))
                {
                    progressDialog.setMessage("Loading");
                    progressDialog.show();
                    firebaseAuth.sendPasswordResetEmail(Useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPasswordAdmin.this,"Reset Password Link Sent Successfully",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgotPasswordAdmin.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(ForgotPasswordAdmin.this,"\t\t\t\t\t\t\t\tProcess Failed\nPlease Enter Registered Email Id",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(ForgotPasswordAdmin.this,"Please Enter your Registered Email Id",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}