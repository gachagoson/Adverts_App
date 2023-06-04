package com.example.blueads;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import com.example.blueads.models.Service; // Import your custom Service class

public class PostServiceActivity extends AppCompatActivity {

    private ImageView imageService;
    private Button buttonUpload;
    private EditText editTextName, editTextLocation, editTextPrice, editTextDescription;
    private Button buttonPost;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    private DatabaseReference databaseReference;
    private String priceText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_service);

        databaseReference = FirebaseDatabase.getInstance().getReference("services");

        imageService = findViewById(R.id.imageService);
        buttonUpload = findViewById(R.id.buttonUpload);
        editTextName = findViewById(R.id.editTextName);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonPost = findViewById(R.id.buttonPost);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open image picker
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });


        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postService();
            }
        });
    }

    // Handle the result of the image picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI
            selectedImageUri = data.getData();

            // Set the selected image to the ImageView
            imageService.setImageURI(selectedImageUri);
        }
    }

    private void postService() {
        // Get the values from the input fields
        String name = editTextName.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String priceString = editTextPrice.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        // Validate the inputs
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(location) || TextUtils.isEmpty(priceString) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price value", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a unique key for the service
        String id = databaseReference.push().getKey();

        if (selectedImageUri != null) {
            // Perform the upload using the selectedImageUri
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imageRef = storageRef.child("images/" + UUID.randomUUID().toString());
            double finalPrice = price;
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get the uploaded image URL
                            Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                            downloadUrlTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUri) {
                                    // Image uploaded successfully
                                    // Get the image URL
                                    String imageUrl = downloadUri.toString();

                                    // Create a Service object
                                    Service service = new Service(id, name, location, finalPrice, description, imageUrl);

                                    // Save the Service object to Firebase Realtime Database
                                    databaseReference.child(id).setValue(service)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(PostServiceActivity.this, "Service posted successfully", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    String errorMessage = e.getMessage();
                                                    Toast.makeText(PostServiceActivity.this, "Failed to post service: " + errorMessage, Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to upload image
                            // Implement your logic for handling the upload failure here
                            Toast.makeText(PostServiceActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Create a Service object without image URL
            Service service = new Service(id, name, location, price, description, "");

            // Save the Service object to Firebase Realtime Database
            databaseReference.child(id).setValue(service)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(PostServiceActivity.this, "Service posted successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String errorMessage = e.getMessage();
                            Toast.makeText(PostServiceActivity.this, "Failed to post service: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
