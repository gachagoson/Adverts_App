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

import com.example.blueads.AdsDetails;
import com.example.blueads.R;
import com.example.blueads.models.Ad;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.AdViewHolder> {

    private Context context;
    private List<Ad> adsList;

    public AdsAdapter(Context context, List<Ad> adsList) {
        this.context = context;
        this.adsList = adsList;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ad, parent, false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        int adsPerRow = 2;
        int startPosition = position * adsPerRow;

        if (startPosition < adsList.size()) {
            Ad ad1 = adsList.get(startPosition);
            holder.bindAd(ad1, 1);
        } else {
            // Hide the views if there are no more ads to display
            holder.cardView1.setVisibility(View.GONE);
        }

        if (startPosition + 1 < adsList.size()) {
            Ad ad2 = adsList.get(startPosition + 1);
            holder.bindAd(ad2, 2);
        } else {
            // Hide the views if there are no more ads to display
            holder.cardView2.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        // Adjust the item count based on the number of ads per row
        return (int) Math.ceil((double) adsList.size() / 2);
    }

    public class AdViewHolder extends RecyclerView.ViewHolder {
        CardView cardView1, cardView2;
        ImageView adImageView1, adImageView2;
        TextView adNameTextView1, adNameTextView2;
        TextView adLocationTextView1, adLocationTextView2;
        TextView adPriceTextView1, adPriceTextView2;
        TextView adDescriptionTextView1, adDescriptionTextView2;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView1 = itemView.findViewById(R.id.cardview1);
            cardView2 = itemView.findViewById(R.id.cardview2);
            adImageView1 = itemView.findViewById(R.id.adImageView1);
            adImageView2 = itemView.findViewById(R.id.adImageView2);
            adNameTextView1 = itemView.findViewById(R.id.adNameTextView1);
            adNameTextView2 = itemView.findViewById(R.id.adNameTextView2);
            adLocationTextView1 = itemView.findViewById(R.id.adLocationTextView1);
            adLocationTextView2 = itemView.findViewById(R.id.adLocationTextView2);
            adPriceTextView1 = itemView.findViewById(R.id.adPriceTextView1);
            adPriceTextView2 = itemView.findViewById(R.id.adPriceTextView2);
            adDescriptionTextView1 = itemView.findViewById(R.id.adDescriptionTextView1);
            adDescriptionTextView2 = itemView.findViewById(R.id.adDescriptionTextView2);
        }

        public void bindAd(Ad ad, int position) {
            if (position == 1) {
                adNameTextView1.setText(ad.getAdName());
                adLocationTextView1.setText(ad.getAdLocation());
                adPriceTextView1.setText(ad.getAdPrice());
                adDescriptionTextView1.setText(ad.getAdDescription());
                Picasso.get().load(ad.getImageUrl()).into(adImageView1);
                cardView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Open AdDetailsActivity and pass the ad ID
                        Intent intent = new Intent(context, AdsDetails.class);
                        intent.putExtra("ad_id", ad.getAdId());
                        context.startActivity(intent);
                    }
                });
            } else if (position == 2) {
                adNameTextView2.setText(ad.getAdName());
                adLocationTextView2.setText(ad.getAdLocation());
                adPriceTextView2.setText(ad.getAdPrice());
                adDescriptionTextView2.setText(ad.getAdDescription());
                Picasso.get().load(ad.getImageUrl()).into(adImageView2);
                cardView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Open AdDetailsActivity and pass the ad ID
                        Intent intent = new Intent(context, AdsDetails.class);
                        intent.putExtra("ad_id", ad.getAdId());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
}
