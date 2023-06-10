package com.example.blueads;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueads.models.Product;

public class ProductDetails extends AppCompatActivity {

    private ImageView imageProduct;
    private TextView textProductName;
    private TextView textProductLocation;
    private TextView textProductPrice;
    private TextView textProductContact;
    private TextView textProductDescription;
    private TextView textProductDatePosted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Get the selected product from the intent
        Product product = getIntent().getParcelableExtra("product");

        // Initialize the views
        imageProduct = findViewById(R.id.imageProduct);
        textProductName = findViewById(R.id.ProductName);
        textProductLocation = findViewById(R.id.ProductLocation);
        textProductPrice = findViewById(R.id.ProductPrice);
        textProductContact = findViewById(R.id.ProductContact);
        textProductDescription = findViewById(R.id.ProductDescription);
        textProductDatePosted = findViewById(R.id.ProductDatePosted);

        // Set the product details in the views
        imageProduct.setImageResource(product.getImage());
        textProductName.setText(product.getName());
        textProductLocation.setText(product.getLocation());
        textProductPrice.setText(String.valueOf(product.getPrice()));
        textProductContact.setText(product.getContact());
        textProductDescription.setText(product.getDescription());
        textProductDatePosted.setText(product.getDatePosted());
    }
}
