package com.example.spideroidreturns.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spideroidreturns.Adapter.RecyclerHomeItemsAdapter;
import com.example.spideroidreturns.Model.HomeItemsModel;
import com.example.spideroidreturns.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    long timeToExit;
    Context context = HomeActivity.this;
    RecyclerView rvHomeItems;
    Toolbar toolbar;
    ArrayList<HomeItemsModel> arrHomeItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        findViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        arrHomeItems.add(new HomeItemsModel("Implicit Intent"));
        arrHomeItems.add(new HomeItemsModel("Notifications"));
        arrHomeItems.add(new HomeItemsModel("Shared Preferences"));
        arrHomeItems.add(new HomeItemsModel("SQLite Database"));
        arrHomeItems.add(new HomeItemsModel("Media Player"));
        arrHomeItems.add(new HomeItemsModel("Sensors"));
        arrHomeItems.add(new HomeItemsModel("Services"));
        arrHomeItems.add(new HomeItemsModel("Firebase Stuff"));
        arrHomeItems.add(new HomeItemsModel("APIs Implementation"));

        RecyclerHomeItemsAdapter recyclerHomeItemsAdapter = new RecyclerHomeItemsAdapter(context, arrHomeItems, position -> {
            if (position >= 0) {
                startActivity(new Intent(context, HomeItemActivity.class).putExtra("position", position));
            }
        });


        rvHomeItems.setLayoutManager(new GridLayoutManager(context, 2));
        rvHomeItems.setAdapter(recyclerHomeItemsAdapter);
    }

    private void findViews() {
        rvHomeItems = findViewById(R.id.recyclerHomeMenu);
        toolbar = findViewById(R.id.toolbar);
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

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - timeToExit > 2500) {
            timeToExit = System.currentTimeMillis();
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }

}