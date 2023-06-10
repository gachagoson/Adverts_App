package com.example.blueads.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueads.R;
import com.example.blueads.adapters.CartItemAdapter;
import com.example.blueads.models.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    public static final String TAG = "CART_FRAGMENT";
    private RecyclerView cartRecyclerView;
    private CartItemAdapter cartItemAdapter;
    private List<CartItem> cartItems = new ArrayList<>();
    private OnAddToCartListener addToCartListener;

    public interface OnAddToCartListener {
        void onAddToCart(CartItem item);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnAddToCartListener) {
            addToCartListener = (OnAddToCartListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnAddToCartListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize the RecyclerView
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);

        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cartRecyclerView.setLayoutManager(layoutManager);

        // Retrieve the cart items
        cartItems = getCartItems();

        // Create the adapter
        cartItemAdapter = new CartItemAdapter(cartItems);

        // Set the adapter
        cartRecyclerView.setAdapter(cartItemAdapter);

        // Handle checkout button click
        Button checkoutButton = view.findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the logic for processing the checkout
            }
        });

        return view;
    }

    // Replace this method with your own logic to retrieve cart items
    private List<CartItem> getCartItems() {
        List<CartItem> items = new ArrayList<>();
        // Add your cart items to the list
        return items;
    }

    public void addItemToCart(CartItem item) {
        cartItems.add(item);
        cartItemAdapter.notifyDataSetChanged();
    }

    // Call this method when the "Add to Cart" button is clicked
    private void onAddToCartButtonClicked(CartItem item) {
        if (addToCartListener != null) {
            addToCartListener.onAddToCart(item);
        }
    }
}
