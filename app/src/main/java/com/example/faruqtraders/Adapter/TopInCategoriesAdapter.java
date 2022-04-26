package com.example.faruqtraders.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.Activities.ProductDetailsActivity;
import com.example.faruqtraders.Model.TopInCategoriesModel;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.ApiResponseModel;

import java.util.List;

public class TopInCategoriesAdapter extends RecyclerView.Adapter<TopInCategoriesAdapter.TopInCategoryViewHolder> {

    Context context;
    ApiResponseModel data;

    public TopInCategoriesAdapter(Context context, ApiResponseModel data) {
        this.context = context;
        this.data = data;
    }

    public TopInCategoriesAdapter() {
    }

    @NonNull
    @Override
    public TopInCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_category_layout, parent, false);
        return new TopInCategoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TopInCategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (data.products.data.size() > 0){
            holder.discount_percent.setText(data.products.data.get(position).discount +" % OFF");
            holder.name.setText(data.products.data.get(position).name);
            //holder.category.setText(data.products.data.get(position).slug);
            holder.main_price.setText(data.products.data.get(position).price + "৳");
            holder.discount_price.setText(data.products.data.get(position).discounted_price.toString() + "৳");
            holder.main_price.setPaintFlags(holder.main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //Toast.makeText(context, data.products.data.get(position).slug, Toast.LENGTH_SHORT).show();
            Glide.with(context).load(data.products.data.get(position).thumbnail).into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("name", data.products.data.get(position).name);
                    intent.putExtra("main_price", data.products.data.get(position).price);
                    intent.putExtra("discount_price", data.products.data.get(position).discounted_price.toString());
                    intent.putExtra("thumbnail", data.products.data.get(position).thumbnail);
                    intent.putExtra("id", data.products.data.get(position).id);
                    intent.putExtra("slug", data.products.data.get(position).slug);
                    context.startActivity(intent);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.products.data.size();
    }

    public class TopInCategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, category, discount_price, main_price, discount_percent;

        public TopInCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.latest_product_name);
            //category = itemView.findViewById(R.id.product_category);
            discount_price = itemView.findViewById(R.id.product_discount_price);
            main_price = itemView.findViewById(R.id.product_main_price);
            discount_percent = itemView.findViewById(R.id.discount_percent);


        }
    }
}
