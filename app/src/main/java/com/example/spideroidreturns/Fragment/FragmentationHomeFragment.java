package com.example.spideroidreturns.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.spideroidreturns.Activity.BottomNavigationActivity;
import com.example.spideroidreturns.Activity.NavDrawerActivity;
import com.example.spideroidreturns.Activity.TabLayoutActivity;
import com.example.spideroidreturns.R;

public class FragmentationHomeFragment extends Fragment {
    Button buttonTabLayout;
    Button btnBottomNavigation;
    Button buttonNavigationDrawer;

    public FragmentationHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewroot = inflater.inflate(R.layout.fragment_fragmentation_home, container, false);

        buttonTabLayout = viewroot.findViewById(R.id.buttonTabLayout);
        btnBottomNavigation = viewroot.findViewById(R.id.btnBottomNavigation);
        buttonNavigationDrawer = viewroot.findViewById(R.id.buttonNavigationDrawer);

        buttonTabLayout.setOnClickListener(v -> startActivity(new Intent(getActivity(), TabLayoutActivity.class)));

        btnBottomNavigation.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), BottomNavigationActivity.class));
        });

        buttonNavigationDrawer.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NavDrawerActivity.class));
        });
        return viewroot;
    }
}