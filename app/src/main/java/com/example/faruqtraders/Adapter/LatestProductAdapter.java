package com.example.faruqtraders.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.os.Parcelable;
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
import com.example.faruqtraders.Model.LatestProductModel;
import com.example.faruqtraders.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LatestProductAdapter extends RecyclerView.Adapter<LatestProductAdapter.LatestProductViewHolder> {

    private List<LatestProductModel> latestProductModelList;
    Context context;

    public LatestProductAdapter(List<LatestProductModel> latestProductModelList, Context context) {
        this.latestProductModelList = latestProductModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public LatestProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_product_layout,parent, false);
        return new LatestProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        LatestProductModel data = latestProductModelList.get(position);

        holder.name.setText(data.getName());
        holder.category.setText(data.getCategory());
        holder.price.setText(data.getPrice());

        Glide.with(context)
                .load(data.getImage())
                .into(holder.imageView)
;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("detail", latestProductModelList.get(position));

                context.startActivity(intent);
                //Toast.makeText(context, data.getId() + " is clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return latestProductModelList.size();
    }

    public void filterList(List<LatestProductModel> list){
        latestProductModelList = list;
        notifyDataSetChanged();
    }

    public class LatestProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, category, price;

        public LatestProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.latest_product_image);
            name = itemView.findViewById(R.id.latest_product_name);
            category = itemView.findViewById(R.id.latest_product_category);
            price = itemView.findViewById(R.id.latest_product_price);

        }
    }


}
