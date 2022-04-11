package com.example.faruqtraders.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faruqtraders.Model.BestSellingModel;
import com.example.faruqtraders.Model.SellProductModel;
import com.example.faruqtraders.R;

import java.util.List;

public class SellProductAdapter extends RecyclerView.Adapter<SellProductAdapter.SellProductViewHolder>{

    private List<SellProductModel> sellProductModelList;

    public SellProductAdapter(List<SellProductModel> sellProductModelList) {
        this.sellProductModelList = sellProductModelList;
    }

    @NonNull
    @Override
    public SellProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sell_product_layout , parent , false);
        return new SellProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellProductViewHolder holder, int position) {
        holder.name.setText(sellProductModelList.get(position).getName());
        holder.category.setText(sellProductModelList.get(position).getCategory());
        holder.price.setText(sellProductModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return sellProductModelList.size();
    }

    public class SellProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, category, price;

        public SellProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.sell_product_image);
            name = itemView.findViewById(R.id.sell_product_name);
            category = itemView.findViewById(R.id.sell_product_category);
            price = itemView.findViewById(R.id.sell_product_price);

        }
    }
}
