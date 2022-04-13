package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.Adapter.RelatedProductAdapter;
import com.example.faruqtraders.Model.LatestProductModel;
import com.example.faruqtraders.Model.RelatedProductModel;
import com.example.faruqtraders.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView product_name, product_category, product_discount_price, product_details_main_price;
    ImageView imageView;

    List<LatestProductModel> latestProductModelList = new ArrayList<>();

    RecyclerView product_details_related_product_recycler_view;

    FloatingActionButton add_button, minus_button;
    AppCompatButton add_to_cart_button, add_to_favourite_button;

    TextView quantityNumberTextView, product_details_main_product_price;
    int count = 1;

    LatestProductModel latestProductModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        /*Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        Bundle extras = getIntent().getExtras();
        //id = Integer.parseInt(extras.getInt("id", id));
        id = extras.getInt("id", id);
        Toast.makeText(this, "Clicked id is "+String.valueOf(id), Toast.LENGTH_SHORT).show();*/

        initialization();
        relateProduct();

        setListener();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof LatestProductModel){
            latestProductModel = (LatestProductModel) object;
        }

        if (latestProductModel != null){
            Glide.with(getApplicationContext()).load(latestProductModel.getImage()).into(imageView);
            product_name.setText(String.valueOf(latestProductModel.getName()));
            product_details_main_price.setText(String.valueOf(latestProductModel.getPrice()));
            product_category.setText(latestProductModel.getCategory());

        }

        //quantityNumberTextView.setText(String.valueOf(count));
        //product_details_main_product_price.setText(String.valueOf("1000"));
        //product_details_main_product_price.setPaintFlags(product_details_main_product_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    private void initialization() {

        product_name = findViewById(R.id.product_details_product_name);
        product_category = findViewById(R.id.product_details_product_category);
        product_details_main_price = findViewById(R.id.product_details_product_main_price);
        product_details_main_price = findViewById(R.id.product_details_product_main_price);
        product_discount_price = findViewById(R.id.product_details_product_dicount_price);

        product_details_related_product_recycler_view = findViewById(R.id.product_details_related_product_recyclerView);

        quantityNumberTextView = findViewById(R.id.quantityNumberTextView);

        imageView = findViewById(R.id.imageBack);
        add_button = findViewById(R.id.add_button);
        minus_button = findViewById(R.id.minus_button);
        add_to_cart_button = findViewById(R.id.add_to_cart_button);
        add_to_favourite_button = findViewById(R.id.add_to_favourite_button);

        imageView = findViewById(R.id.imageView);

    }

    private void relateProduct() {

        product_details_related_product_recycler_view.setHasFixedSize(true);
        product_details_related_product_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<RelatedProductModel> relatedProductModelList = new ArrayList<>();

        relatedProductModelList.add(new RelatedProductModel(R.drawable.img,"Capilano Manuka Active Honey 340g","1057.3৳"));
        relatedProductModelList.add(new RelatedProductModel(R.drawable.sell,"Capilano Manuka Active Honey 340g","1057.3৳"));
        relatedProductModelList.add(new RelatedProductModel(R.drawable.feature,"Capilano Manuka Active Honey 340g","1057.3৳"));

        RelatedProductAdapter relatedProductAdapter = new RelatedProductAdapter(relatedProductModelList, this);

        product_details_related_product_recycler_view.setAdapter(relatedProductAdapter);

    }

    private void setListener(){
        add_button.setOnClickListener(this);
        minus_button.setOnClickListener(this);
        add_to_cart_button.setOnClickListener(this);
        add_to_favourite_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_button:
                increaseCount();
                break;

            case R.id.minus_button:
                decreaseCount();
                break;

            case R.id.add_to_cart_button:
                Toast.makeText(this, "Add to cart Button is clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.add_to_favourite_button:
                Toast.makeText(this, "Add to favourite Button is clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                return;
        }
    }



    private void Counter(){

        quantityNumberTextView.setText(String.valueOf(count));

    }

    private void increaseCount(){
        count = count+1;
        quantityNumberTextView.setText(String.valueOf(count));
    }

    private void decreaseCount(){
        if (count == 1){

            return;
        }
        else {
            count = count-1;
            quantityNumberTextView.setText(String.valueOf(count));
        }

    }

}