package com.example.faruqtraders.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.ApiResponseModel;
import com.example.faruqtraders.Response.VisitedProductResponse;

public class PeopleAreAlsoLookingForAdapter extends RecyclerView.Adapter<PeopleAreAlsoLookingForAdapter.SuggestProductViewHolder> {

    Context context;
    VisitedProductResponse data;

    public PeopleAreAlsoLookingForAdapter(Context context, VisitedProductResponse data) {
        this.context = context;
        this.data = data;
    }

    public PeopleAreAlsoLookingForAdapter() {
    }

    @NonNull
    @Override
    public SuggestProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout , parent , false);
        return new SuggestProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestProductViewHolder holder, int position) {

        if (data.products.size() > 0){

            holder.name.setText(data.products.get(position).name);
            //holder.category.setText(data.products.data.get(position).slug);
            holder.main_price.setText(data.products.get(position).price + "৳");
            holder.discount_price.setText(data.products.get(position).discounted_price.toString() + "৳");
            holder.main_price.setPaintFlags(holder.main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            Glide.with(context).load(data.products.get(position).thumbnail).into(holder.imageView);

        }
    }

    @Override
    public int getItemCount() {
        return data.products.size();
    }


    public class SuggestProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name, category, discount_price, main_price;

        public SuggestProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.latest_product_name);
            //category = itemView.findViewById(R.id.product_category);
            discount_price = itemView.findViewById(R.id.product_discount_price);
            main_price = itemView.findViewById(R.id.product_main_price);

        }
    }

}
