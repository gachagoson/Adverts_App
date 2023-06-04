package com.example.blueads.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueads.R;
import com.example.blueads.adapters.ServiceAdapter;
import com.example.blueads.models.Service;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {

    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    private List<Service> serviceList;
    private ServiceAdapter serviceAdapter;

    private TextView textViewNoServices;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("services");

        // Initialize the list of services and the adapter
        serviceList = new ArrayList<>();
        serviceAdapter = new ServiceAdapter(getActivity(), serviceList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        // Initialize the TextView for displaying "No services" message
        textViewNoServices = view.findViewById(R.id.textViewNoServices);

        // Initialize the RecyclerView for displaying the services
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewServices);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(serviceAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Attach the ChildEventListener to listen for changes in the services
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // A new service is added
                Service service = dataSnapshot.getValue(Service.class);
                serviceList.add(service);
                serviceAdapter.notifyDataSetChanged();
                updateUI();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // A service is changed
                Service service = dataSnapshot.getValue(Service.class);
                int index = getServiceIndex(service.getId());
                if (index != -1) {
                    serviceList.set(index, service);
                    serviceAdapter.notifyItemChanged(index);
                    updateUI();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // A service is removed
                Service service = dataSnapshot.getValue(Service.class);
                int index = getServiceIndex(service.getId());
                if (index != -1) {
                    serviceList.remove(index);
                    serviceAdapter.notifyItemRemoved(index);
                    updateUI();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // A service is moved
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error occurred
            }
        };

        databaseReference.addChildEventListener(childEventListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Detach the ChildEventListener
        if (childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
        }
    }

    private int getServiceIndex(String serviceId) {
        for (int i = 0; i < serviceList.size(); i++) {
            Service service = serviceList.get(i);
            if (service.getId().equals(serviceId)) {
                return i;
            }
        }
        return -1; // Return -1 if service is not found
    }


    private void updateUI() {
        if (serviceList.isEmpty()) {
            textViewNoServices.setVisibility(View.VISIBLE);
        } else {
            textViewNoServices.setVisibility(View.GONE);
        }
    }
}
