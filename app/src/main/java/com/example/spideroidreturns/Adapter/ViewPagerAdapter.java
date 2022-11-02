package com.example.spideroidreturns.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.spideroidreturns.Fragment.CallGreenFragment;
import com.example.spideroidreturns.Fragment.CameraBlackFragment;
import com.example.spideroidreturns.Fragment.ChatsPrimaryFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new CameraBlackFragment();
        }
        else if (position == 1){
            return new ChatsPrimaryFragment();
        }
        else {
            return new CallGreenFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
     if (position == 0){
         return "Camera";
     }
     else if (position == 1){
         return "Chats";
     }
     else {
         return "Calls";
     }
    }
}
