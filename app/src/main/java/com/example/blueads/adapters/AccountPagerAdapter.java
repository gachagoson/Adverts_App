package com.example.blueads.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.blueads.fragments.MyOrders;
import com.example.blueads.fragments.PaymentsFragment;

public class AccountPagerAdapter extends FragmentPagerAdapter {

    public AccountPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyOrders();
            case 1:
                return new PaymentsFragment();
            // Add more cases for additional sections

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2; // Return the total number of sections
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My Orders";
            case 1:
                return "Payments";
            // Add more cases for additional sections

            default:
                return null;
        }
    }
}
