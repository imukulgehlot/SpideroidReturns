package com.example.spideroidreturns.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.spideroidreturns.R;

public class ImplicitIntentFragment extends Fragment {

    public ImplicitIntentFragment() {
        // Required empty public constructor
    }

    Button buttonSms;
    Button buttonCall;
    Button buttonEmail;
    Button buttonShare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_implicit_intent, container, false);

        findIDs(v);
        clickHandlers();
        return v;
    }

    private void clickHandlers() {
        String phoneNumber = "+917062085218";


        buttonCall.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_DIAL)
                    .setData(Uri.parse("tel: " + phoneNumber)));
        });


        buttonSms.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms: " + phoneNumber)).putExtra("sms_body", "Maine abhi aapse sampark karne ki koshish ki, kripya turant call karein!"));
        });


        buttonEmail.setOnClickListener(v -> {
            Intent iMail = new Intent(Intent.ACTION_SEND);
            iMail.setType("message/rfc822");
            iMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"imukulgehlot@gmail.com", "support@google.com"});
            iMail.putExtra(Intent.EXTRA_SUBJECT, "Hey there, leave!");
            iMail.putExtra(Intent.EXTRA_TEXT, "1\n2\n3\n4\nGrab the disco dancer");
            startActivity(Intent.createChooser(iMail, "Send Email via"));
        });

        buttonShare.setOnClickListener(v -> {
            Intent iShare = new Intent(Intent.ACTION_SEND);
            iShare.setType("text/plain");
            iShare.putExtra(Intent.EXTRA_TEXT, "Holla chikca guapa!" +
                    "\nConnect with me over here\n" +
                    "https://www.linkedin.com/imukulgehlot");
            startActivity(iShare);
        });
    }

    private void findIDs(View v) {
        buttonSms = v.findViewById(R.id.smses);
        buttonCall = v.findViewById(R.id.call);
        buttonEmail = v.findViewById(R.id.email);
        buttonShare = v.findViewById(R.id.share);
    }
}