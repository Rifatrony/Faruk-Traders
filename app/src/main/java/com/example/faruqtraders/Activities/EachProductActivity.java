package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.EachProductResponse;

import java.util.List;

public class EachProductActivity extends AppCompatActivity {

    TextView name, category, sku, main_price, discount_price;

    List<EachProductResponse> eachProductResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_product);

        name = findViewById(R.id.product_details_name);
        category = findViewById(R.id.product_category);
        sku = findViewById(R.id.product_sku_number);
        main_price = findViewById(R.id.product_main_price);
        discount_price = findViewById(R.id.product_discount_price);

    }

    private void received_product_details(){

    }



}