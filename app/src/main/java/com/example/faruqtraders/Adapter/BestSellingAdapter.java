package com.example.faruqtraders.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faruqtraders.Activities.ProductDetailsActivity;
import com.example.faruqtraders.Model.BestSellingModel;
import com.example.faruqtraders.R;

import java.util.List;

public class BestSellingAdapter extends RecyclerView.Adapter<BestSellingAdapter.BestSellerViewHolder> {

    private List<BestSellingModel> bestSellingList;
    public BestSellingAdapter(List<BestSellingModel> bestSellerList){
        this.bestSellingList = bestSellerList;
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_selling_layout , parent , false);
        return new BestSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerViewHolder holder, int position) {
        holder.name.setText(bestSellingList.get(position).getName());
        holder.category.setText(bestSellingList.get(position).getCategory());
        holder.price.setText(bestSellingList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.name.getContext(), ProductDetailsActivity.class);
                holder.name.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bestSellingList.size();
    }

    public class BestSellerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, category, price;
        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.best_selling_product_image);
            name = itemView.findViewById(R.id.best_selling_product_name);
            category = itemView.findViewById(R.id.best_selling_product_category);
            price = itemView.findViewById(R.id.best_selling_product_price);
        }
    }

}
