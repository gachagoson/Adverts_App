package com.example.blueads.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueads.ProductDetails;
import com.example.blueads.R;
import com.example.blueads.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = productList.get(position);

        // Set the product details in the view holder
        holder.textProductName1.setText(product.getName());
        holder.textProductLocation1.setText(product.getLocation());
        holder.textProductPrice1.setText(String.valueOf(product.getPrice()));
        holder.textProductContact1.setText(product.getContact());
        holder.textProductDescription1.setText(product.getDescription());
        holder.textProductDatePosted1.setText(product.getDatePosted());

        // Set click listener to open product details
        holder.cardViewProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the product details activity
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });

        // Set the second product details in the view holder
        holder.textProductName2.setText(product.getName());
        holder.textProductLocation2.setText(product.getLocation());
        holder.textProductPrice2.setText(String.valueOf(product.getPrice()));
        holder.textProductContact2.setText(product.getContact());
        holder.textProductDescription2.setText(product.getDescription());
        holder.textProductDatePosted2.setText(product.getDatePosted());

        // Set click listener to open product details for the second product
        holder.cardViewProduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the product details activity
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private CardView cardViewProduct1;
        private CardView cardViewProduct2;
        private ImageView imageProduct1;
        private ImageView imageProduct2;
        private TextView textProductName1;
        private TextView textProductLocation1;
        private TextView textProductPrice1;
        private TextView textProductContact1;
        private TextView textProductDescription1;
        private TextView textProductDatePosted1;
        private TextView textProductName2;
        private TextView textProductLocation2;
        private TextView textProductPrice2;
        private TextView textProductContact2;
        private TextView textProductDescription2;
        private TextView textProductDatePosted2;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewProduct1 = itemView.findViewById(R.id.cardViewProduct1);
            cardViewProduct2 = itemView.findViewById(R.id.cardViewProduct2);
            imageProduct1 = itemView.findViewById(R.id.imageProduct1);
            imageProduct2 = itemView.findViewById(R.id.imageProduct2);
            textProductName1 = itemView.findViewById(R.id.textProductName1);
            textProductLocation1 = itemView.findViewById(R.id.textProductLocation1);
            textProductPrice1 = itemView.findViewById(R.id.textProductPrice1);
            textProductContact1 = itemView.findViewById(R.id.textProductContact1);
            textProductDescription1 = itemView.findViewById(R.id.textProductDescription1);
            textProductDatePosted1 = itemView.findViewById(R.id.textProductDatePosted1);
            textProductName2 = itemView.findViewById(R.id.textProductName2);
            textProductLocation2 = itemView.findViewById(R.id.textProductLocation2);
            textProductPrice2 = itemView.findViewById(R.id.textProductPrice2);
            textProductContact2 = itemView.findViewById(R.id.textProductContact2);
            textProductDescription2 = itemView.findViewById(R.id.textProductDescription2);
            textProductDatePosted2 = itemView.findViewById(R.id.textProductDatePosted2);
        }
    }
}
