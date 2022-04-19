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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.Activities.ProductDetailsActivity;
import com.example.faruqtraders.Model.BestSellingModel;
import com.example.faruqtraders.R;

import java.util.List;

public class BestSellingAdapter extends RecyclerView.Adapter<BestSellingAdapter.BestSellerViewHolder> {

    private List<BestSellingModel> bestSellingModelList;
    private Context context;

    public BestSellingAdapter(List<BestSellingModel> bestSellingModelList, Context context) {
        this.bestSellingModelList = bestSellingModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent , false);
        return new BestSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerViewHolder holder, @SuppressLint("RecyclerView") int position) {

        BestSellingModel data = bestSellingModelList.get(position);

        holder.name.setText(data.getName());
        //holder.category.setText(data.getCategory());
        holder.main_price.setText(String.valueOf(data.getMain_price()));
        holder.discount_price.setText(String.valueOf(data.getDiscount_price()));
        holder.main_price.setPaintFlags(holder.main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        Glide.with(context)
                .load(data.getImage())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("sell_details", data);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return bestSellingModelList.size();
    }

    public class BestSellerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, category, main_price, discount_price;
        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.latest_product_name);
            //category = itemView.findViewById(R.id.product_category);
            main_price = itemView.findViewById(R.id.product_main_price);
            discount_price = itemView.findViewById(R.id.product_discount_price);

        }
    }

}
