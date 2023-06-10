package com.example.blueads.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueads.R;
import com.example.blueads.models.CartItem;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    private List<CartItem> cartItems;

    public CartItemAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        // Bind the cart item data to the views in the cart item layout
        holder.adNameTextView.setText(cartItem.getAdName());
        holder.adLocationTextView.setText(cartItem.getAdLocation());
        holder.adPriceTextView.setText(cartItem.getAdPrice());
        holder.adDescriptionTextView.setText(cartItem.getAdDescription());

        // Set click listeners for the buttons in the cart item layout
        holder.removeFromCartButton.setOnClickListener(v -> {
            // Handle remove from cart button click
            // Implement the logic to remove the item from the cart
        });

        holder.contactButton.setOnClickListener(v -> {
            // Handle contact button click
            // Implement the logic to start a chat with the seller
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView adNameTextView;
        TextView adLocationTextView;
        TextView adPriceTextView;
        TextView adDescriptionTextView;
        Button removeFromCartButton;
        Button contactButton;

        CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            adNameTextView = itemView.findViewById(R.id.adNameTextView);
            adLocationTextView = itemView.findViewById(R.id.adLocationTextView);
            adPriceTextView = itemView.findViewById(R.id.adPriceTextView);
            adDescriptionTextView = itemView.findViewById(R.id.adDescriptionTextView);
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartButton);
            contactButton = itemView.findViewById(R.id.contactButton);
        }
    }
}
