package com.example.blueads;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;

public class PostProduct extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;

    private ImageView imageProduct;
    private Button buttonUpload;
    private EditText editTextName, editTextLocation, editTextPrice, editTextContact, editTextDescription;
    private TextView textViewDate;
    private Button buttonPost;

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);

        // Initialize views
        imageProduct = findViewById(R.id.imageProduct);
        buttonUpload = findViewById(R.id.buttonUpload);
        editTextName = findViewById(R.id.editTextName);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextContact = findViewById(R.id.editTextContact);
        editTextDescription = findViewById(R.id.editTextDescription);
        textViewDate = findViewById(R.id.textViewDate);
        buttonPost = findViewById(R.id.buttonPost);

        // Set a placeholder image for product photo
        imageProduct.setImageResource(R.drawable.placeholder);

        // Set current date as default date posted
        calendar = Calendar.getInstance();
        String currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "-" +
                (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.YEAR);
        textViewDate.setText("Date Posted: " + currentDate);

        // Set click listener for date picker
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set click listener for upload image button
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        // Set click listener for post button
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String name = editTextName.getText().toString();
                String location = editTextLocation.getText().toString();
                String price = editTextPrice.getText().toString();
                String contact = editTextContact.getText().toString();
                String description = editTextDescription.getText().toString();
                String datePosted = textViewDate.getText().toString().replace("Date Posted: ", "");

                // TODO: Save the product information to the database or perform desired actions

                // Clear the input fields
                editTextName.setText("");
                editTextLocation.setText("");
                editTextPrice.setText("");
                editTextContact.setText("");
                editTextDescription.setText("");
                imageProduct.setImageResource(R.drawable.placeholder);

                // Display a success message or perform desired actions
                // (e.g., show a toast, navigate to another activity)
            }
        });
    }

    // Method to show the date picker dialog
    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                year,
                month,
                day
        );
        datePickerDialog.show();
    }

    // Listener for date picker dialog
    private DatePickerDialog.OnDateSetListener getDateSetListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                textViewDate.setText("Date Posted: " + selectedDate);
            }
        };
    }

    // Method to open the image picker
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    // Set the selected image to the ImageView
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageProduct.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
