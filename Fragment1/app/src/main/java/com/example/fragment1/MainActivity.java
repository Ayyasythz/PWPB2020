package com.example.fragment1;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fragment1.ui.dashboard.DashboardFragment;
import com.example.fragment1.ui.home.HomeFragment;


import com.example.fragment1.ui.notifications.NotificationsFragment;
import com.example.fragment1.ui.profile.ProfileFragments;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.navigation_notifications:
                fragment = new NotificationsFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragments();
                    break;
    

        }
        return loadFragment(fragment);

    }

    private  boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }


}