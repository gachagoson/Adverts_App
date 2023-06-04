package com.example.blueads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blueads.R;
import com.example.blueads.models.Ad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdsDetails extends AppCompatActivity {

    private DatabaseReference adsRef;

    private ImageView adImageView;
    private TextView adNameTextView;
    private TextView adLocationTextView;
    private TextView adPriceTextView;
    private TextView adDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_details);

        adsRef = FirebaseDatabase.getInstance().getReference("ads");

        adImageView = findViewById(R.id.adImageView);
        adNameTextView = findViewById(R.id.adNameTextView);
        adLocationTextView = findViewById(R.id.adLocationTextView);
        adPriceTextView = findViewById(R.id.adPriceTextView);
        adDescriptionTextView = findViewById(R.id.adDescriptionTextView);

        // Get the ad ID from the intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ad_id")) {
            String adId = intent.getStringExtra("ad_id");
            fetchAdFromDatabase(adId);
        } else {
            // Handle case when ad ID is not provided
        }
    }

    private void fetchAdFromDatabase(String adId) {
        adsRef.child(adId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the ad object from the snapshot
                    Ad ad = snapshot.getValue(Ad.class);
                    if (ad != null) {
                        // Update the UI with the ad details
                        updateUI(ad);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });
    }

    private void updateUI(Ad ad) {
        // Update the UI with the ad details (e.g., set text in TextViews, load image into ImageView, etc.)
        adNameTextView.setText(ad.getAdName());
        adLocationTextView.setText(ad.getAdLocation());
        adPriceTextView.setText(ad.getAdPrice());
        adDescriptionTextView.setText(ad.getAdDescription());

        // Load the image using an image loading library like Picasso or Glide
        Picasso.get().load(ad.getImageUrl()).into(adImageView);
    }
}
