package com.example.spideroidreturns.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.spideroidreturns.Fragment.FragmentationHomeFragment;
import com.example.spideroidreturns.Fragment.ImplicitIntentFragment;
import com.example.spideroidreturns.Fragment.NotificationFragment;
import com.example.spideroidreturns.Fragment.SharedPrefFragment;
import com.example.spideroidreturns.R;

public class HomeItemActivity extends AppCompatActivity {
    Context context = HomeItemActivity.this;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            int position = getIntent().getIntExtra("position", 99);

            switch (position) {
                case 0:
                    AddFragment(new ImplicitIntentFragment());
                    toolbar.setSubtitle("Implict Intents");
                    break;

                case 1:
                    AddFragment(new NotificationFragment());
                    toolbar.setSubtitle("Notifyin You Again");
                    break;

                case 2:
                    AddFragment(new SharedPrefFragment());
                    toolbar.setSubtitle("Shared Prefs");
                    break;

                case 3:
                    AddFragment(new FragmentationHomeFragment());
                    toolbar.setSubtitle("Fragz");
                    break;
                case 4:
                    startActivity(new Intent(context, DataBumActivity.class));
                    finish();
                    break;

                default:
                    Toast.makeText(getApplicationContext(), "neah, not in existence yet", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void AddFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}