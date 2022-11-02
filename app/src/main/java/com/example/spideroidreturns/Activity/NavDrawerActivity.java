package com.example.spideroidreturns.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.spideroidreturns.Fragment.HomieFragment;
import com.example.spideroidreturns.Fragment.NotifiylyFragment;
import com.example.spideroidreturns.Fragment.ProfielyFragment;
import com.example.spideroidreturns.Fragment.SearchieFragment;
import com.example.spideroidreturns.R;
import com.google.android.material.navigation.NavigationView;

public class NavDrawerActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(NavDrawerActivity.this,
                drawerLayout, toolbar, R.string.Open_Drawer, R.string.Close_Drawer);

        toggle.syncState();

        loadFrag(new HomieFragment(), false);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                loadFrag(new HomieFragment(), false);
            } else if (id == R.id.nav_notifications)
                loadFrag(new NotifiylyFragment(), true);
            else if (id == R.id.nav_search)
                loadFrag(new SearchieFragment(), true);
            else
                loadFrag(new ProfielyFragment(), true);

            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    private void loadFrag(Fragment fragment, boolean doReplace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (doReplace)
            ft.replace(R.id.container, fragment);
        else
            ft.add(R.id.container, fragment);
        ft.commit();
    }
}