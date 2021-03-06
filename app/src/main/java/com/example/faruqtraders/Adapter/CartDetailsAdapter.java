package com.example.faruqtraders.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.CartResponseModel;

import java.util.ArrayList;

public class CartDetailsAdapter extends RecyclerView.Adapter<CartDetailsAdapter.CartViewHolder> {

    Context context;
    CartResponseModel cartResponseModel;

    public CartDetailsAdapter(Context context, CartResponseModel cartResponseModel) {
        this.context = context;
        this.cartResponseModel = cartResponseModel;
    }

    public CartDetailsAdapter() {
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        if (cartResponseModel.data.size() > 0 ){
            holder.name.setText(cartResponseModel.data.get(position).product.name);
            holder.price.setText(cartResponseModel.data.get(position).product.price);
            holder.quantity.setText(cartResponseModel.data.get(position).quantity);

            System.out.println("Name : " + cartResponseModel.data.get(position).product.name);

            int finalResult = Integer.parseInt(cartResponseModel.data.get(position).price) * Integer.parseInt(String.valueOf(cartResponseModel.data.get(position).quantity));            holder.sub_total_amount_each_cart.setText(String.valueOf(finalResult) + " Tk.");
        }
    }

    @Override
    public int getItemCount() {
        return cartResponseModel.data.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, quantity, sub_total_amount_each_cart;
        ImageView cartProductImage, increase_image_button, decrease_image_button;

        ImageView deleteCartButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cartProductName);
            price = itemView.findViewById(R.id.cartProductPrice);
            quantity = itemView.findViewById(R.id.cartProductQuantity);
            cartProductImage = itemView.findViewById(R.id.cartProductImage);
            deleteCartButton = itemView.findViewById(R.id.deleteCartButtonId);
            increase_image_button = itemView.findViewById(R.id.increase_image_button);
            decrease_image_button = itemView.findViewById(R.id.decrease_image_button);
            sub_total_amount_each_cart = itemView.findViewById(R.id.sub_total_amount_each_cart);

        }
    }

}
