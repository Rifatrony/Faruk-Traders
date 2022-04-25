package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.Adapter.RelatedProductAdapter;
import com.example.faruqtraders.Model.RelatedProductModel;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.AddToCartResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView product_name, product_category, product_discount_price, product_details_main_price, quantityNumberTextView;
    ImageView imageView;

    RecyclerView recyclerView;

    FloatingActionButton add_button, minus_button;
    AppCompatButton add_to_cart, add_to_favourite;
    AppCompatImageView imageBack;

    List<AddToCartResponse.Data> data;

    int count = 1;

    //LatestProductModel latestProductModel = null;

    //BestSellingModel bestSellingModel = null;

    //ApiResponseModel data = null;

    String name, main_price, discount_price, thumbnail, id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initialization();
        relateProduct();
        setListener();

        received_product_details();

        //setData();
        /*position = getIntent().getIntExtra("position", 0);
        System.out.println("Product Name is "+ apiResponseData.products.data.get(position).name);*/
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

    private void setData(){

        /*product_name.setText(apiResponseModel.products.data.get(position).name);
        product_details_main_price.setText(apiResponseModel.products.data.get(position).price);
        product_discount_price.setText(apiResponseModel.products.data.get(position).discounted_price.toString());
        Glide.with(this).load(thumbnail).into(imageView);*/

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

    @SuppressLint("SetTextI18n")
    private void received_product_details(){

        name = getIntent().getStringExtra("name");
        main_price = getIntent().getStringExtra("main_price");
        discount_price = getIntent().getStringExtra("discount_price");
        thumbnail = getIntent().getStringExtra("thumbnail");
        id = getIntent().getStringExtra("id");

        product_name.setText(name);
        product_details_main_price.setText(main_price+" ৳");
        product_discount_price.setText(discount_price+" ৳");
        product_details_main_price.setPaintFlags(product_details_main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(this).load(thumbnail).into(imageView);
        quantityNumberTextView.setText(String.valueOf(count));

    }

    @SuppressLint("NonConstantResourceId")
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
        }
    }

    /*Added to the cart*/

    public void addToCart(){

        //showToast("Added to Cart Successfully");

        String name = product_name.getText().toString().trim();
        String category = product_category.getText().toString().trim();
        String main_price = product_details_main_price.getText().toString().trim();
        String discount_price = product_discount_price.getText().toString().trim();

        System.out.println("Product Name is : " + name +"\n"+ "Category is : " + category +"\n"+ "Quantity is : " + count);
        System.out.println("Main Price in double is : " + main_price);
        System.out.println("Discount Price is : " + discount_price);

        showToast("Added Cart item are : \n" +"\n"+name +"\n" +category +"\n"+ main_price +"\n"+ discount_price +"\n"+ count + "\n" + id);

        Call<AddToCartResponse> call = RetrofitClient.getRetrofitClient().addProductToCart(name, category, main_price, discount_price,id);

        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                if (response.isSuccessful()){
                    showToast("Success");
                }
                else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {

            }
        });


      /* RetrofitClient.getRetrofitClient().addProductToCart(data.get(position).product.toString(),String.valueOf(data.get(position).total),String.valueOf(data.get(position).price),String.valueOf(data.get(position).quantity)).enqueue(new Callback<List<AddToCartResponse>>() {
           @Override
           public void onResponse(Call<List<AddToCartResponse>> call, Response<List<AddToCartResponse>> response) {
               if (response.isSuccessful()){
                   showToast("Successfully Added");
               }

               else {
                   showToast(response.errorBody().toString());
               }
           }

           @Override
           public void onFailure(Call<List<AddToCartResponse>> call, Throwable t) {
               showToast(t.getLocalizedMessage());
           }
       });*/


    }

    private void increaseCount(){
        count++;
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