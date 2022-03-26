package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPass;
    public static String email;
    public static String FirstTime;
    public static double count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.ptadd);
        password = (EditText)findViewById(R.id.etpass);
        login = (Button)findViewById(R.id.btn1);
        TextView user = (TextView) findViewById(R.id.etNewUser);
        forgotPass = (TextView)findViewById(R.id.tvforgot);

        email = name.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(MainActivity.this);
        FirebaseUser old_user = firebaseAuth.getCurrentUser();

        if(old_user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,Navigation.class));
        }

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForgotPassword.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || password.getText().toString().isEmpty() )
                {
                    Toast.makeText(MainActivity.this,"Please Enter all the Details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    validate(name.getText().toString(),password.getText().toString());
                }

            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newuser = new Intent(MainActivity.this,NewUserSignUP.class);
                startActivity(newuser);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void validate(String Name, String Password)
    {
        progressDialog.setMessage("Loading");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Name,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    OneTimeAccess();
                    //checkemailverification();
                    //startActivity(new Intent(MainActivity.this,Navigation.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    public void OneTimeAccess()
    {
        SharedPreferences preferences = getSharedPreferences("PREFRENCE",MODE_PRIVATE);
        FirstTime = preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("Yes"))
        {
            startActivity(new Intent(MainActivity.this,Navigation.class));
            Toast.makeText(MainActivity.this, "Login Successfull" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            startActivity(new Intent(MainActivity.this,StartProfile.class));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }
    }

    private void checkemailverification()
    {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        boolean email = firebaseUser.isEmailVerified();
        //count++;
        if(email)
        {
            OneTimeAccess();
//            count++;
//            if(count == 1)
//            {
//                finish();
//                //Toast.makeText(MainActivity.this, "Login Successfull" , Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this,StartProfile.class));
//                overridePendingTransition(R.anim.from_left,R.anim.from_right);
//            }
//            else
//            {
//                finish();
//                Toast.makeText(MainActivity.this, "Login Successfull" , Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this,Navigation.class));
//                overridePendingTransition(R.anim.from_left,R.anim.from_right);
//            }
//            finish();
//            Toast.makeText(MainActivity.this, "Login Successfull " +count , Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this,StartProfile.class));
        }
        else
        {
            Toast.makeText(MainActivity.this,"Please Verify Your Email ",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }
}