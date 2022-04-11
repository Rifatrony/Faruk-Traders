package com.example.faruqtraders.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.Model.TopCategoriesMoreProductModel;
import com.example.faruqtraders.R;

import java.util.List;

public class TopCategoriesMoreProductAdapter extends RecyclerView.Adapter<TopCategoriesMoreProductAdapter.TopCategoriesMoreProductViewHolder> {

    Context context;
    List<TopCategoriesMoreProductModel> topCategoriesMoreProductModelList;

    public TopCategoriesMoreProductAdapter(Context context, List<TopCategoriesMoreProductModel> topCategoriesMoreProductModelList) {
        this.context = context;
        this.topCategoriesMoreProductModelList = topCategoriesMoreProductModelList;
    }

    @NonNull
    @Override
    public TopCategoriesMoreProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.top_in_category_more_product_layout, parent, false);

        return new TopCategoriesMoreProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopCategoriesMoreProductViewHolder holder, int position) {
        TopCategoriesMoreProductModel data = topCategoriesMoreProductModelList.get(position);

        holder.name.setText(data.getName());
        holder.main_price.setText(String.valueOf(data.getMain_price()));
        holder.discount_price.setText(String.valueOf(data.getDiscount_price()));

        Glide.with(context).load(data.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return topCategoriesMoreProductModelList.size();
    }

    public class TopCategoriesMoreProductViewHolder extends RecyclerView.ViewHolder{

        TextView name, main_price, discount_price;
        ImageView imageView;

        public TopCategoriesMoreProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.top_in_categories_more_product_name);
            main_price = itemView.findViewById(R.id.main_price);
            discount_price = itemView.findViewById(R.id.discount_price);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

}
