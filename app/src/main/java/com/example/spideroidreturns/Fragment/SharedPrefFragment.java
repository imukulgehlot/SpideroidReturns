package com.example.spideroidreturns.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spideroidreturns.R;

public class SharedPrefFragment extends Fragment {

    public SharedPrefFragment() {
        /* Required empty public constructor */
    }

    TextView tvTexttoProtect;
    TextView buttonSave;
    TextView buttonExit;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shared_pref, container, false);

        tvTexttoProtect = view.findViewById(R.id.tvTexttoProtect);
        buttonExit = view.findViewById(R.id.buttonExit);
        buttonSave = view.findViewById(R.id.buttonSave);



        buttonExit.setOnClickListener(v -> {

            
        });
        return view;




        /*
            SMS
            Notify
            Toolbar Subtitle
         */
    }
}