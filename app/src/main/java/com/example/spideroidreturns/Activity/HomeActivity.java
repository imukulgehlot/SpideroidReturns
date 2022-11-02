package com.example.spideroidreturns.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
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
        notifer();

        arrHomeItems.add(new HomeItemsModel("Implicit Intent"));
        arrHomeItems.add(new HomeItemsModel("Notifications"));
        arrHomeItems.add(new HomeItemsModel("Shared Preferences"));
        arrHomeItems.add(new HomeItemsModel("Fragmentation"));
        arrHomeItems.add(new HomeItemsModel("SQLite Database"));
        arrHomeItems.add(new HomeItemsModel("Geocoder"));
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

    private void notifer() {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context)
                    .setSmallIcon(R.drawable.ic_spidroid)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img_amazeman))
                    .setSubText("Sub Text")
                    .setContentTitle("Content Title")
                    .setContentText("Content Text")
                    .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, TabLayoutActivity.class),
                            PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
                    .setChannelId("CID")
                    .build();
            nm.createNotificationChannel(new NotificationChannel("CID", "Aapano Channel Hai Saa", NotificationManager.IMPORTANCE_DEFAULT));
        } else {
            notification = new Notification.Builder(context)
                    .setSmallIcon(R.drawable.ic_spidroid)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img_amazeman))
                    .setSubText("Sub Text")
                    .setContentTitle("Content Title")
                    .setContentText("Content Text")
                    .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, TabLayoutActivity.class),
                            PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();
        }
        nm.notify(0, notification);
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