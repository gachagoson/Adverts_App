package com.example.blueads.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.blueads.Alibaba;
import com.example.blueads.MainActivity;
import com.example.blueads.PostAdsActivity;
import com.example.blueads.PostProduct;
import com.example.blueads.PostServiceActivity;
import com.example.blueads.R;

public class MyOrders extends Fragment {

    ImageView imageView, Imageview1,Imageview2;

    public MyOrders() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.my_orders, container, false);


        LinearLayout linearLayout = rootView.findViewById(R.id.manager);
        ImageView imageView = linearLayout.findViewById(R.id.post);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Post Ads page
                Intent intent = new Intent(getActivity(), PostAdsActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView2 = linearLayout.findViewById(R.id.product);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Post Ads page
                Intent intent = new Intent(getActivity(), PostProduct.class);
                startActivity(intent);
            }
        });
        ImageView imageView1 = linearLayout.findViewById(R.id.service);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Post Ads page
                Intent intent = new Intent(getActivity(), PostServiceActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    // Fragment implementation goes here
}

