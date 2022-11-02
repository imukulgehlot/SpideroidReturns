package com.example.spideroidreturns.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.spideroidreturns.R;

public class SharedPrefFragment extends Fragment {

    public SharedPrefFragment() {
        /* Required empty public constructor */
    }

    TextView tvTexttoProtect;
    Button buttonSave;
    Button buttonExit;
    Button buttonFetch;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shared_pref, container, false);

        tvTexttoProtect = view.findViewById(R.id.tvTexttoProtect);
        buttonExit = view.findViewById(R.id.buttonExit);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonFetch = view.findViewById(R.id.buttonFetch);

        buttonExit.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("Exit Spideroid Returns");
            alertDialog.setMessage("Are you sure you want say b'bye?");
            alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);
            alertDialog.setPositiveButton("Sure", (dialog, which) -> {
                getActivity().finishAffinity();
                System.exit(0);
            });
            alertDialog.setNegativeButton("Nopes", (dialog, which) -> {

            });
            alertDialog.show();
        });

        buttonSave.setOnClickListener(v -> {
            if (!tvTexttoProtect.getText().toString().isEmpty()) {
                SharedPreferences.Editor editor = requireContext().getSharedPreferences("PREF", Context.MODE_PRIVATE).edit();
                editor.putString("OKOKOK", tvTexttoProtect.getText().toString());
                editor.apply();
                Toast.makeText(getContext(), "Text saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Put something to save :)", Toast.LENGTH_SHORT).show();
            }
        });

        buttonFetch.setOnClickListener(v -> {
            SharedPreferences preferences = requireContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
            String texty = preferences.getString("OKOKOK", "");
            if (texty != null && !texty.isEmpty()) {
                tvTexttoProtect.setText(texty);
                Toast.makeText(getContext(), "Text restored from Prefs!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Prefs are empty!", Toast.LENGTH_SHORT).show();
            }

        });
        return view;

    }
}