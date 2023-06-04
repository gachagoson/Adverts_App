package com.example.blueads.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueads.R;
import com.example.blueads.adapters.AdsAdapter;
import com.example.blueads.models.Ad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Explore extends Fragment {

    private RecyclerView adsRecyclerView;
    private AdsAdapter adsAdapter;
    private List<Ad> adsList;

    private DatabaseReference adsRef;

    public Explore() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        // Initialize Firebase Realtime Database reference
        adsRef = FirebaseDatabase.getInstance().getReference("ads");

        // Initialize RecyclerView and its properties
        adsRecyclerView = view.findViewById(R.id.exploreRecyclerView);
        adsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adsRecyclerView.setHasFixedSize(true);

        // Initialize ads list and adapter
        adsList = new ArrayList<>();
        adsAdapter = new AdsAdapter(getContext(), adsList);
        adsRecyclerView.setAdapter(adsAdapter);

        // Fetch ads from the database and add them to the list
        // This code should be placed where you want to fetch the ads from the database

        // For example:
        adsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adsList.clear();
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Ad ad = adSnapshot.getValue(Ad.class);
                    adsList.add(ad);
                }
                adsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error if needed
            }
        });

        return view;
    }
}
