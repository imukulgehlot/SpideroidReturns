package com.example.spideroidreturns.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.spideroidreturns.Activity.SplashActivity;
import com.example.spideroidreturns.R;

import java.io.Serializable;

public class NotificationFragment extends Fragment implements Serializable {

    Button btnNotify;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        findViews(v);


        return v;
    }



    private void findViews(View v) {
        btnNotify = v.findViewById(R.id.btnNotify);
    }
}