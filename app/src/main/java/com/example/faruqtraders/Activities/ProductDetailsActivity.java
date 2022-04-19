package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
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
import com.example.faruqtraders.Model.BestSellingModel;
import com.example.faruqtraders.Model.LatestProductModel;
import com.example.faruqtraders.Model.RelatedProductModel;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.ApiResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView product_name, product_category, product_discount_price, product_details_main_price, quantityNumberTextView;
    ImageView imageView;

    RecyclerView recyclerView;

    FloatingActionButton add_button, minus_button;
    AppCompatButton add_to_cart, add_to_favourite;
    AppCompatImageView imageBack;

    int count = 1;

    LatestProductModel latestProductModel = null;
    BestSellingModel bestSellingModel = null;

    ApiResponseModel data = null;

    String name, main_price, discount_price, thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initialization();
        relateProduct();
        setListener();

        received_latest_product_details();
        received_best_selling_details();
        receive_sale_details();

    }

    private void initialization() {

        product_name = findViewById(R.id.product_details_product_name);
        product_category = findViewById(R.id.product_details_product_category);
        product_details_main_price = findViewById(R.id.product_details_product_main_price);
        product_discount_price = findViewById(R.id.product_details_product_dicount_price);
        imageView = findViewById(R.id.imageView);

        recyclerView = findViewById(R.id.product_details_related_product_recyclerView);

        quantityNumberTextView = findViewById(R.id.quantityNumberTextView);

        imageBack = findViewById(R.id.imageBackId);
        add_button = findViewById(R.id.add_button);
        minus_button = findViewById(R.id.minus_button);
        add_to_cart = findViewById(R.id.add_to_cart_button);
        add_to_favourite = findViewById(R.id.add_to_favourite_button);

    }

    private void relateProduct() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<RelatedProductModel> relatedProductModelList = new ArrayList<>();

        relatedProductModelList.add(new RelatedProductModel(R.drawable.img,"Capilano Manuka Active Honey 340g","1057.3৳"));
        relatedProductModelList.add(new RelatedProductModel(R.drawable.sell,"Capilano Manuka Active Honey 340g","1057.3৳"));
        relatedProductModelList.add(new RelatedProductModel(R.drawable.feature,"Capilano Manuka Active Honey 340g","1057.3৳"));

        RelatedProductAdapter relatedProductAdapter = new RelatedProductAdapter(relatedProductModelList, this);

        recyclerView.setAdapter(relatedProductAdapter);

    }

    private void setListener(){
        add_button.setOnClickListener(this);
        minus_button.setOnClickListener(this);
        add_to_cart.setOnClickListener(this);
        add_to_favourite.setOnClickListener(this);
        imageBack.setOnClickListener(this);
    }


    private void received_latest_product_details(){
        /*final Object object = getIntent().getSerializableExtra("details");

        if (object instanceof LatestProductModel){
            latestProductModel = (LatestProductModel) object;
        }

        if (latestProductModel != null){

            Glide.with(getApplicationContext()).load(latestProductModel.getImage()).into(imageView);
            product_name.setText(String.valueOf(latestProductModel.getName()));
            product_details_main_price.setText(latestProductModel.getMain_price() +" ৳");

            product_category.setText(latestProductModel.getCategory());
            product_discount_price.setText(latestProductModel.getDiscount_price() +" ৳");
            product_details_main_price.setPaintFlags(product_details_main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            quantityNumberTextView.setText(String.valueOf(count));
        }*/
    }

    private void received_best_selling_details(){

        name = getIntent().getStringExtra("name");
        main_price = getIntent().getStringExtra("main_price");
        discount_price = getIntent().getStringExtra("discount_price");
        thumbnail = getIntent().getStringExtra("thumbnail");

        product_name.setText(name);
        product_details_main_price.setText(main_price+" ৳");
        product_discount_price.setText(discount_price+" ৳");
        product_details_main_price.setPaintFlags(product_details_main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(this).load(thumbnail).into(imageView);
        quantityNumberTextView.setText(String.valueOf(count));

    }

    private void receive_sale_details(){
        /*final Object object = getIntent().getSerializableExtra("sale_details");
        if (object instanceof ApiResponseModel){
            data = (ApiResponseModel) object;

        }
        if (data != null){
            System.out.println("Data in activity file is ====================> " +data.products);
            Toast.makeText(this, "Item id is ::: " + data, Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imageBackId:
                onBackPressed();
                break;

            case R.id.add_button:
                increaseCount();
                break;

            case R.id.minus_button:
                decreaseCount();
                break;

            case R.id.add_to_cart_button:
                addToCart();
                break;

            case R.id.add_to_favourite_button:
                Toast.makeText(this, "Add to favourite Button is clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                return;
        }
    }

    /*Added to the cart*/

    public void addToCart(){

        showToast("Added to Cart Successfully");

        String name = product_name.getText().toString().trim();
        String category = product_category.getText().toString().trim();
        String main_price = product_details_main_price.getText().toString().trim();
        String discount_price = product_discount_price.getText().toString().trim();

        System.out.println("Product Name is : " + name +"\n"+ "Category is : " + category +"\n"+ "Quantity is : " + count);
        System.out.println("Main Price in double is : " + main_price);
        System.out.println("Discount Price is : " + discount_price);

        showToast("Added Cart item are : \n" +"\n"+name +"\n" +category +"\n"+ main_price +"\n"+ discount_price +"\n"+ count);

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

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}