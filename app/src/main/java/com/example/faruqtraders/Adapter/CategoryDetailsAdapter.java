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

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoriesDetailsViewHolder> {

    Context context;
    ApiResponseModel apiResponseModel;

    public CategoryDetailsAdapter(Context context, ApiResponseModel apiResponseModel) {
        this.context = context;
        this.apiResponseModel = apiResponseModel;
    }

    @NonNull
    @Override
    public CategoriesDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout , parent , false);
        return new CategoriesDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesDetailsViewHolder holder, int position) {
        if (apiResponseModel.products.data.size() > 0){
            holder.name.setText(apiResponseModel.products.data.get(position).name);
            //holder.category.setText(data.products.data.get(position).slug);
            holder.main_price.setText(apiResponseModel.products.data.get(position).price + "৳");
            holder.discount_price.setText(apiResponseModel.products.data.get(position).discounted_price.toString() + "৳");
            holder.main_price.setPaintFlags(holder.main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            Glide.with(context).load(apiResponseModel.products.data.get(position).thumbnail).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return apiResponseModel.products.data.size();
    }

    public static class CategoriesDetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, main_price, discount_price;

        public CategoriesDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);

            name = itemView.findViewById(R.id.product_name);
            main_price = itemView.findViewById(R.id.product_main_price);
            discount_price = itemView.findViewById(R.id.product_discount_price);

        }
    }
}
