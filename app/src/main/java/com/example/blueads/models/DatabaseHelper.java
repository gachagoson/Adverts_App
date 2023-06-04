package com.example.blueads.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseHelper {
    private DatabaseReference databaseReference;

    public DatabaseHelper() {
        // Initialize the Firebase Realtime Database reference
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("services");
    }

    public void addService(Service service) {
        String serviceId = databaseReference.push().getKey();
        databaseReference.child(serviceId).setValue(service);
    }

    public void getAllServices(ValueEventListener listener) {
        databaseReference.addValueEventListener(listener);
    }
}
