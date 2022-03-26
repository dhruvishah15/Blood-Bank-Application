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

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText passEmailreset;
    private Button resetPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        passEmailreset = (EditText)findViewById(R.id.etEmailAdd);
        resetPassword = (Button)findViewById(R.id.btnResetPass);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(ForgotPassword.this);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Useremail = passEmailreset.getText().toString().trim();

                if(Useremail.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this,"Please Enter your Registered Email Id",Toast.LENGTH_LONG).show();
                }
                else
                {
                    progressDialog.setMessage("Loading");
                    progressDialog.show();
                    firebaseAuth.sendPasswordResetEmail(Useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this,"Reset Password Link Sent Successfully",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(ForgotPassword.this,"\t\t\t\t\t\t\t\tProcess Failed\nPlease Enter Registered Email Id",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });

    }
}