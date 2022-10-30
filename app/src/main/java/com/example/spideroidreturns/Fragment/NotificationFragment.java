package com.example.spideroidreturns.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


        btnNotify.setOnClickListener(vieww -> {
            notifying();

        });

        return v;
    }

    private void notifying() {
        NotificationManager nm = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(getContext())
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img_amazeman))
                    .setSmallIcon(R.drawable.ic_spidroid)
                    .setContentTitle("Aagya, Notification")
                    .setContentText("Jao sb sojao yaaro")
                    .setSubText("Hmm")
                    .setChannelId("Spideroid Ro Channel")
                    .setContentIntent(PendingIntent.getActivity(getContext(), 67, new Intent(getContext(), SplashActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();

            nm.createNotificationChannel(new NotificationChannel("Spideroid Ro Channel", "Spideroid Nonsense Channel", NotificationManager.IMPORTANCE_DEFAULT));
        } else {
            notification = new Notification.Builder(getContext())
                    .setContentTitle("Aagya, Notification")
                    .setSmallIcon(R.drawable.ic_spidroid)
                    .setContentText("Jao sb sojao yaaro")
                    .setContentIntent(PendingIntent.getActivity(getContext(), 67, new Intent(getContext(), SplashActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();
        }
        nm.notify(66, notification);
    }

    private void findViews(View v) {
        btnNotify = v.findViewById(R.id.btnNotify);
    }
}