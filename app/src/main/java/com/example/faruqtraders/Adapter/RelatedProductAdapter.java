package com.example.faruqtraders.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.Model.RelatedProductModel;
import com.example.faruqtraders.R;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.RelatedProductViewHolder> {

    List<RelatedProductModel> relatedProductModelList;
    Context context;

    public RelatedProductAdapter(List<RelatedProductModel> relatedProductModelList, Context context) {
        this.relatedProductModelList = relatedProductModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RelatedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.related_product_layout, parent, false);
        return new RelatedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductViewHolder holder, int position) {
        RelatedProductModel data = relatedProductModelList.get(position);

        holder.product_name.setText(data.getProduct_name());
        holder.product_price.setText(data.getProduct_price());
        Glide.with(context)
                .load(data.getImage())
                .into(holder.related_product_imageView);


    }

    @Override
    public int getItemCount() {
        return relatedProductModelList.size();
    }

    public class RelatedProductViewHolder extends RecyclerView.ViewHolder{

        TextView product_name, product_price;
        ImageView related_product_imageView;

        public RelatedProductViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.related_product_product_name);
            product_price = itemView.findViewById(R.id.related_product_product_price);
            related_product_imageView = itemView.findViewById(R.id.related_product_imageview);

        }
    }

}
