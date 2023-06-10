package com.example.blueads;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blueads.fragments.CartFragment;
import com.example.blueads.models.Ad;
import com.example.blueads.models.CartItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdsDetails extends AppCompatActivity implements CartFragment.OnAddToCartListener {

    private DatabaseReference adsRef;

    private ImageView adImageView;
    private TextView adNameTextView;
    private TextView adLocationTextView;
    private TextView adPriceTextView;
    private TextView adDescriptionTextView;
    private Button addToCartButton;
    private Button contactButton;

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
        addToCartButton = findViewById(R.id.addToCartButton);
        contactButton = findViewById(R.id.contactButton);

        // Get the ad ID and Ad object from the intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ad_id") && intent.hasExtra("ad")) {
            String adId = intent.getStringExtra("ad_id");
            Ad ad = intent.getParcelableExtra("ad");
            fetchAdFromDatabase(adId);
        } else {
            // Handle case when ad ID or Ad object is not provided
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }
        });
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

    private void addToCart() {
        try {
            Log.d("AddToCart", "addToCart() method called");

            // Retrieve the Ad object from the intent extras
            Ad ad = getIntent().getParcelableExtra("ad");

            if (ad != null) {
                // Create a new CartItem object with the ad details
                CartItem item = new CartItem(
                        ad.getAdName(),
                        ad.getAdLocation(),
                        ad.getAdPrice(),
                        ad.getAdDescription()
                );

                // Start the CartItem activity and pass the CartItem object
                Intent cartIntent = new Intent(AdsDetails.this, CartItem.class);
                cartIntent.putExtra("cart_item", item);
                startActivity(cartIntent);

                Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Log the error message
            Log.e("AddToCart", "Error adding item to cart: " + e.getMessage(), e);
            // Handle the exception or display an error message to the user
            // ...
        }
    }




    private void openChat() {
        // Open chat logic
        Toast.makeText(this, "Opening chat", Toast.LENGTH_SHORT).show();
    }

    private CartFragment getCartFragment() {
        // Find the CartFragment by tag
        return (CartFragment) getSupportFragmentManager().findFragmentByTag(CartFragment.TAG);
    }

    @Override
    public void onAddToCart(CartItem item) {
        // Create a new CartItem object with the ad details
        CartItem cartItem = new CartItem(
                item.getAdName(),
                item.getAdLocation(),
                item.getAdPrice(),
                item.getAdDescription()
        );

        // Pass the CartItem to the CartFragment
        CartFragment cartFragment = getCartFragment();
        if (cartFragment != null) {
            try {
                cartFragment.addItemToCart(cartItem);
                Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                String errorMessage = e.getMessage();
                Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error: Cart not found. Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }
}
