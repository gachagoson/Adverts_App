package com.example.blueads.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        for (int i = 0; i < adsPerRow; i++) {
            int adIndex = startPosition + i;

            if (adIndex < adsList.size()) {
                Ad ad = adsList.get(adIndex);

                if (i == 0) {
                    holder.adNameTextView1.setText(ad.getAdName());
                    holder.adLocationTextView1.setText(ad.getAdLocation());
                    holder.adPriceTextView1.setText(ad.getAdPrice());
                    holder.adDescriptionTextView1.setText(ad.getAdDescription());
                    Picasso.get().load(ad.getImageUrl()).into(holder.adImageView1);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Get the clicked ad's position
                            int position = holder.getAdapterPosition();

                            if (position != RecyclerView.NO_POSITION) {
                                // Get the ad ID based on the position
                                String adId = adsList.get(position * adsPerRow).getAdId();

                                // Open AdDetailsActivity and pass the ad ID
                                Intent intent = new Intent(context, AdsDetails.class);
                                intent.putExtra("ad_id", adId);
                                context.startActivity(intent);
                            }
                        }
                    });
                } else if (i == 1) {
                    holder.adNameTextView2.setText(ad.getAdName());
                    holder.adLocationTextView2.setText(ad.getAdLocation());
                    holder.adPriceTextView2.setText(ad.getAdPrice());
                    holder.adDescriptionTextView2.setText(ad.getAdDescription());
                    Picasso.get().load(ad.getImageUrl()).into(holder.adImageView2);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Get the clicked ad's position
                            int position = holder.getAdapterPosition();

                            if (position != RecyclerView.NO_POSITION) {
                                // Get the ad ID based on the position
                                String adId = adsList.get(position * adsPerRow + 1).getAdId();

                                // Open AdDetailsActivity and pass the ad ID
                                Intent intent = new Intent(context, AdsDetails.class);
                                intent.putExtra("ad_id", adId);
                                context.startActivity(intent);
                            }
                        }
                    });
                }
            } else {
                // Hide the views if there are no more ads to display
                if (i == 0) {
                    holder.adNameTextView1.setVisibility(View.GONE);
                    holder.adLocationTextView1.setVisibility(View.GONE);
                    holder.adPriceTextView1.setVisibility(View.GONE);
                    holder.adDescriptionTextView1.setVisibility(View.GONE);
                    holder.adImageView1.setVisibility(View.GONE);
                } else if (i == 1) {
                    holder.adNameTextView2.setVisibility(View.GONE);
                    holder.adLocationTextView2.setVisibility(View.GONE);
                    holder.adPriceTextView2.setVisibility(View.GONE);
                    holder.adDescriptionTextView2.setVisibility(View.GONE);
                    holder.adImageView2.setVisibility(View.GONE);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        // Adjust the item count based on the number of ads per row
        return (int) Math.ceil((double) adsList.size() / 2);
    }

    public class AdViewHolder extends RecyclerView.ViewHolder {
        TextView adNameTextView1, adLocationTextView1, adPriceTextView1, adDescriptionTextView1;
        ImageView adImageView1;
        TextView adNameTextView2, adLocationTextView2, adPriceTextView2, adDescriptionTextView2;
        ImageView adImageView2;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            adNameTextView1 = itemView.findViewById(R.id.adNameTextView1);
            adLocationTextView1 = itemView.findViewById(R.id.adLocationTextView1);
            adPriceTextView1 = itemView.findViewById(R.id.adPriceTextView1);
            adDescriptionTextView1 = itemView.findViewById(R.id.adDescriptionTextView1);
            adImageView1 = itemView.findViewById(R.id.adImageView1);
            adNameTextView2 = itemView.findViewById(R.id.adNameTextView2);
            adLocationTextView2 = itemView.findViewById(R.id.adLocationTextView2);
            adPriceTextView2 = itemView.findViewById(R.id.adPriceTextView2);
            adDescriptionTextView2 = itemView.findViewById(R.id.adDescriptionTextView2);
            adImageView2 = itemView.findViewById(R.id.adImageView2);
        }
    }

}
