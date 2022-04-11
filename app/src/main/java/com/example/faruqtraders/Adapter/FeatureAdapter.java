package com.example.faruqtraders.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.Model.BestSellingModel;
import com.example.faruqtraders.Model.FeatureModel;
import com.example.faruqtraders.R;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureProductViewHolder>{

    List<FeatureModel> featureModelList;

    public FeatureAdapter(List<FeatureModel> featureModelList) {
        this.featureModelList = featureModelList;
    }

    @NonNull
    @Override
    public FeatureProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_layout , parent , false);
        return new FeatureProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureProductViewHolder holder, int position) {
        FeatureModel data = featureModelList.get(position);
        holder.name.setText(data.getName());
        holder.price.setText(String.valueOf(data.getPrice()));
        holder.discount_price.setText(String.valueOf(data.getDiscounted_price()));
        Glide
                .with(holder.name.getContext())
                .load(data.getThumbnail()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return featureModelList.size();
    }

    public class FeatureProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, category, price, discount_price;

        public FeatureProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.feature_product_image);
            name = itemView.findViewById(R.id.feature_product_name);
            category = itemView.findViewById(R.id.feature_product_category);
            price= itemView.findViewById(R.id.feature_product_price);
            discount_price= itemView.findViewById(R.id.feature_product_main_price);

        }
    }

}
