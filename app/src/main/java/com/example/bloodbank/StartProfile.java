package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class StartProfile extends AppCompatActivity implements View.OnClickListener{

    private DatePickerDialog picker;
    private EditText date;
    private EditText firstname,lastname,age,contantNo;
    Button register;
    public static String first,last,Age,bloodGroup,Gender,contact,Date;
    private RadioGroup radioGroup;
    private RadioButton radioButton,male,female,other;
    private FirebaseAuth firebaseAuth;
    private Spinner spinner;
    SharedPreferences sp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_profile);

        sp = getSharedPreferences("MyuserPrefs", Context.MODE_PRIVATE);

        firstname = (EditText)findViewById(R.id.et_first);
        lastname = (EditText)findViewById(R.id.et_last);
        age = (EditText)findViewById(R.id.et_age);
        contantNo = (EditText)findViewById(R.id.et_phoneNumber);
        register = (Button)findViewById(R.id.btn_register);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        //for selected Radio Buttons
        male = (RadioButton)findViewById(R.id.rb_male);
        female = (RadioButton)findViewById(R.id.rb_female);
        other = (RadioButton)findViewById(R.id.rb_other);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        other.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        //DropDown List of Blood Groups
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartProfile.this,android.R.layout.simple_spinner_item
                ,getResources().getStringArray(R.array.blood_group));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Select"))
                {
                    //do nothing
                }
                else
                {
                    bloodGroup = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Dates
        date = (EditText)findViewById(R.id.et_birthdate);
        date.setInputType(InputType.TYPE_NULL);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(StartProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+ "/" +(month + 1)+ "/" +year);
                    }
                },year,day,month);
                picker.show();
            }
        });

        //sending database to the firebase
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    senduserData();
                    //saveData();
                    startActivity(new Intent(StartProfile.this,Navigation.class));
                    Toast.makeText(StartProfile.this, "Login Successfull" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private boolean validate()
    {
        boolean result;
        result=false;
        first = firstname.getText().toString();
        last = lastname.getText().toString();
        Age = age.getText().toString();
        contact = contantNo.getText().toString();
        Date = date.getText().toString();

        if(first.isEmpty() || last.isEmpty() || Age.isEmpty() || Date.isEmpty() || contact.isEmpty())
        {
            Toast.makeText(StartProfile.this,"Please Enter All the Details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }

        return result;
    }

    @Override
    public void onBackPressed() {

        SharedPreferences preferences = getSharedPreferences("PREFRENCE",MODE_PRIVATE);
        MainActivity.FirstTime = preferences.getString("FirstTimeInstall","");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FirstTimeInstall","");
        editor.apply();
        startActivity(new Intent(StartProfile.this,MainActivity.class));

//        if(validate())
//        {
//            MainActivity.count=0;
//            startActivity(new Intent(StartProfile.this,MainActivity.class));
//        }
//        else
//        {
//            MainActivity.count=0;
//            startActivity(new Intent(StartProfile.this,MainActivity.class));
//            //Toast.makeText(StartProfile.this,"Please Fill all the Details",Toast.LENGTH_SHORT).show();
//        }
    }

    private void senduserData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference();
        UserProfile userProfile = new UserProfile();
        userProfile.setAge(Age);
        userProfile.setGender(Gender);
        userProfile.setBloodGroup(bloodGroup);
        userProfile.setContactNo(contact);
        userProfile.setFirstName(first);
        userProfile.setLastName(last);
        userProfile.setDob(Date);
        if (myref.child("users").child(firebaseAuth.getUid()).setValue(userProfile).isSuccessful()){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    //for saving data
//    public void saveData()
//    {
//        SharedPreferences.Editor editor = sp.edit();
//
//        editor.putString("firstname",first);
//        editor.putString("lastname",last);
//        editor.putString("age",Age);
//        editor.putString("contactNo",contact);
//        editor.putString("date",Date);
//        editor.putString("blood",bloodGroup);
//        editor.putString("gender",Gender);
//        editor.commit();
//        Toast.makeText(StartProfile.this, "Data saved Through Shared Preferences", Toast.LENGTH_SHORT).show();
//    }




   //For Checked Button
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.rb_male)
        {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(radioId);
            Gender = radioButton.getText().toString();
        }
        else if (id == R.id.rb_female)
        {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(radioId);
            Gender = radioButton.getText().toString();
        }
        else
        {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(radioId);
            Gender = radioButton.getText().toString();
        }
    }
}