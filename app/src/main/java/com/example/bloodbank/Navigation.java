package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Navigation extends AppCompatActivity{

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private FirebaseAuth firebaseAuth;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    List<Model> itemList;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        drawer = (DrawerLayout)findViewById(R.id.drawers);
        navigationView = (NavigationView)findViewById(R.id.nav_host_fragement);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(Navigation.this,drawer,toolbar,R.string.open,R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragement_container,new Notification());
        fragmentTransaction.commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId())
                {
                    case R.id.logoutmenu:
                    {
                        Logout();
                        break;
                    }
                    case R.id.history:
                    {
                        Toast.makeText(Navigation.this,"You are in History Fragement",Toast.LENGTH_SHORT).show();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragement_container,new History());
                        fragmentTransaction.commit();
                        break;
                    }
                    case R.id.settings:
                    {
                        Toast.makeText(Navigation.this,"You are in Settings Fragement",Toast.LENGTH_SHORT).show();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragement_container,new Settings());
                        fragmentTransaction.commit();
                        break;
                    }
                    case R.id.notification:
                    {
                        Toast.makeText(Navigation.this,"You are in Notification Fragement",Toast.LENGTH_SHORT).show();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragement_container,new Notification());
                        fragmentTransaction.commit();
                        break;
                    }
                    case R.id.share:
                    {
                        share();
                        break;
                    }
                    case R.id.profile:
                    {
                        startActivity(new Intent(Navigation.this,Profile.class));
                        break;
                    }
                }
                return true;
            }
        });
    }
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Navigation.this,MainActivity.class));
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    public void share()
    {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Blood Bank Application");
            String sharemsg = "https://play.google.com/store/apps/details?id="+ BuildConfig.APPLICATION_ID+"\n\n";
            intent.putExtra(Intent.EXTRA_TEXT,sharemsg);
            startActivity(Intent.createChooser(intent,"Share By"));
        }
        catch (Exception e)
        {
            Toast.makeText(Navigation.this,"Error Occured",Toast.LENGTH_SHORT).show();
        }
    }

}