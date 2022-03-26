package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity
{
    private EditText AdminId;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView forgotPwd;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AdminId = (EditText) findViewById(R.id.etAdmin);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.loginbtn);
        forgotPwd= (TextView) findViewById(R.id.fp);

        Info.setText("    No. of attempts remaining: 5");

        progressDialog = new ProgressDialog(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(AdminId.getText().toString(), Password.getText().toString());
            }
        });
        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });
    }

    private void validate(String adminId, String adminPwd) {
        progressDialog.setMessage("Logging in");
        progressDialog.show();

        if (adminId.equals("admin123@gmail.com") && adminPwd.equals("12345")) {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, Navigation.class));
        } else {
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            counter--;
            Info.setText("    No. of attempts remaining: " + counter);
            progressDialog.dismiss();
            if (counter == 0) {
                Login.setEnabled(false);
            }
        }

    };
}

