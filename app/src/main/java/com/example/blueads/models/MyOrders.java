package com.example.blueads.models;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blueads.R;

public class MyOrders extends LinearLayout {

    private TextView titleTextView;
    private ImageView ordersIcon;
    private ImageView soldIcon;
    private ImageView boughtIcon;
    private ImageView pendingIcon;

    public MyOrders(Context context) {
        super(context);
        init();
    }

    public MyOrders(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyOrders(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.my_orders, this);
        titleTextView = findViewById(R.id.titleTextView);
        ordersIcon = findViewById(R.id.ordersIcon);
        soldIcon = findViewById(R.id.soldIcon);
        boughtIcon = findViewById(R.id.boughtIcon);
        pendingIcon = findViewById(R.id.pendingIcon);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setOrdersIcon(int resId) {
        ordersIcon.setImageResource(resId);
    }

    public void setSoldIcon(int resId) {
        soldIcon.setImageResource(resId);
    }

    public void setBoughtIcon(int resId) {
        boughtIcon.setImageResource(resId);
    }

    public void setPendingIcon(int resId) {
        pendingIcon.setImageResource(resId);
    }
}
