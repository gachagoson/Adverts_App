package com.example.blueads;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blueads.models.Ad;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class PostAdsActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText adNameEditText, adLocationEditText, adPriceEditText, adDescriptionEditText;

    private TextView adDateTextView;
    private Button postAdButton, upload_ad;

    private ImageView imageView;
    private Uri imageUri;
    private DatabaseReference adsRef;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ads);

        // Initialize Firebase Realtime Database reference
        adsRef = FirebaseDatabase.getInstance().getReference("ads");

        // Initialize Firebase Storage reference
        storageRef = FirebaseStorage.getInstance().getReference();

        // Find views
        upload_ad = findViewById(R.id.uploadButton);
        adNameEditText = findViewById(R.id.adNameEditText);
        adLocationEditText = findViewById(R.id.adLocationEditText);
        adPriceEditText = findViewById(R.id.adPriceEditText);
        adDescriptionEditText = findViewById(R.id.adDescriptionEditText);
        adDateTextView = findViewById(R.id.adDateTextView);
        postAdButton = findViewById(R.id.postAdButton);
        imageView = findViewById(R.id.imageView);

        // Set a click listener for the Post Ad button
        postAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAdToDatabase();
            }
        });

        // Set a click listener for the image view to select an image from storage
        upload_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void postAdToDatabase() {
        // Get input values
        String adName = adNameEditText.getText().toString().trim();
        String date = adDateTextView.getText().toString().trim();
        String adLocation = adLocationEditText.getText().toString().trim();
        String adPrice = adPriceEditText.getText().toString().trim();
        String adDescription = adDescriptionEditText.getText().toString().trim();

        // Check if an image is selected
        if (imageUri != null) {
            // Generate a unique ID for the ad
            String adId = adsRef.push().getKey();

            // Create a StorageReference with a unique name for the image file
            final StorageReference imageRef = storageRef.child("ad_images/" + adId + ".jpg");

            // Upload the image to Firebase Storage
            imageRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get the download URL of the uploaded image
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUri) {
                                    // Create an Ad object with the download URL of the image
                                    Ad ad = new Ad(adId, adName, adLocation, adPrice, adDescription, date);
                                    ad.setImageUrl(downloadUri.toString());

                                    // Save the ad to the database
                                    adsRef.child(adId).setValue(ad)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // Display a success message
                                                    Toast.makeText(PostAdsActivity.this, "Ad posted successfully", Toast.LENGTH_SHORT).show();

                                                    // Clear input fields
                                                    adNameEditText.setText("");
                                                    adDateTextView.setText("");
                                                    adLocationEditText.setText("");
                                                    adPriceEditText.setText("");
                                                    adDescriptionEditText.setText("");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Handle the error
                                                    Toast.makeText(PostAdsActivity.this, "Failed to post ad", Toast.LENGTH_SHORT).show();
                                                    Log.e("PostAdsActivity", "Error posting ad to database", e);
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle the error
                            Toast.makeText(PostAdsActivity.this, "Failed to upload image to storage", Toast.LENGTH_SHORT).show();
                            Log.e("PostAdsActivity", "Error uploading image to storage", e);
                        }
                    });
        } else {
            // If no image is selected, proceed without uploading an image
            String adId = adsRef.push().getKey();
            Ad ad = new Ad(adId, adName, adLocation, adPrice, adDescription, date);
            adsRef.child(adId).setValue(ad)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Display a success message
                            Toast.makeText(PostAdsActivity.this, "Ad posted successfully", Toast.LENGTH_SHORT).show();

                            // Clear input fields
                            adNameEditText.setText("");
                            adLocationEditText.setText("");
                            adPriceEditText.setText("");
                            adDescriptionEditText.setText("");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle the error
                            Toast.makeText(PostAdsActivity.this, "Failed to post ad", Toast.LENGTH_SHORT).show();
                            Log.e("PostAdsActivity", "Error posting ad to database", e);
                        }
                    });
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAdItemClick(View view) {
        // Get the clicked ad ID or any other unique identifier
        String adId = "your_ad_id";

        // Create an intent to open the ad details activity
        Intent intent = new Intent(this, AdsDetails.class);

        // Pass the ad ID as an extra to the intent
        intent.putExtra("adId", adId);

        // Start the ad details activity
        startActivity(intent);
    }

}
