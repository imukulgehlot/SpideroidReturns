package com.example.spideroidreturns.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.spideroidreturns.Fragment.HomieFragment;
import com.example.spideroidreturns.Fragment.NotifiylyFragment;
import com.example.spideroidreturns.Fragment.ProfielyFragment;
import com.example.spideroidreturns.Fragment.SearchieFragment;
import com.example.spideroidreturns.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    BottomNavigationView bnBottomsUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bnBottomsUp = findViewById(R.id.bnBottomsUp);

        bnBottomsUp.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    loadFrag(new HomieFragment(), false);
                    break;
                case R.id.nav_notifications:
                    loadFrag(new NotifiylyFragment(), true);
                    break;
                case R.id.nav_search:
                    loadFrag(new SearchieFragment(), true);
                    break;
                case R.id.nav_profile:
                    loadFrag(new ProfielyFragment(), true);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
            }

            return true;
        });

        bnBottomsUp.setSelectedItemId(R.id.nav_home);
    }

    public void loadFrag(Fragment fragment, boolean doReplace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (doReplace) {
            ft.replace(R.id.container, fragment);
        } else {
            ft.add(R.id.container, fragment);
        }
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}