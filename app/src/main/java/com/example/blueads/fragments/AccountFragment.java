package com.example.blueads.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.blueads.R;
import com.example.blueads.adapters.AccountPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AccountFragment extends Fragment {

    private ImageView avatarImageView;
    private TextView usernameTextView;
    private TabLayout tabLayout;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Find views
        avatarImageView = view.findViewById(R.id.avatarImageView);
        usernameTextView = view.findViewById(R.id.usernameTextView);
        tabLayout = view.findViewById(R.id.tabLayout);

        // Set up the TabLayout
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        AccountPagerAdapter adapter = new AccountPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Set the username and avatar (replace with actual data)
        usernameTextView.setText("John Doe");
        avatarImageView.setImageResource(R.drawable.avatar_placeholder);

        return view;
    }

    // AccountPagerAdapter class implementation goes here
}
