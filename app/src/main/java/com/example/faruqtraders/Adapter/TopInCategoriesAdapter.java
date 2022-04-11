package com.example.faruqtraders.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faruqtraders.Model.TopInCategoriesModel;
import com.example.faruqtraders.R;

import java.util.List;

public class TopInCategoriesAdapter extends RecyclerView.Adapter<TopInCategoriesAdapter.TopInCategoryViewHolder> {

    List<TopInCategoriesModel> topInCategoriesModelList;

    public TopInCategoriesAdapter(List<TopInCategoriesModel> topInCategoriesModelList) {
        this.topInCategoriesModelList = topInCategoriesModelList;
    }

    @NonNull
    @Override
    public TopInCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_in_categories, parent, false);
        return new TopInCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopInCategoryViewHolder holder, int position) {

        holder.name.setText(topInCategoriesModelList.get(position).getName());
        holder.category.setText(topInCategoriesModelList.get(position).getCategory());
        holder.price.setText(topInCategoriesModelList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return topInCategoriesModelList.size();
    }

    public class TopInCategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, category, price;

        public TopInCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.top_in_categories_product_image);
            name = itemView.findViewById(R.id.top_in_categories_product_name);
            category = itemView.findViewById(R.id.top_in_categories_product_category);
            price = itemView.findViewById(R.id.top_in_categories_product_price);


        }
    }
}
