package com.example.faruqtraders.Activities;

import static com.example.faruqtraders.Activities.AllCategoryActivity.apiResponseData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.API.ApiInterface;
import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.Adapter.RelatedProductAdapter;

import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.AddCartResponse;
import com.example.faruqtraders.Response.AddToCartResponse;
import com.example.faruqtraders.Response.ApiResponseModel;
import com.example.faruqtraders.Response.ProductDetailsResponseModel;
import com.example.faruqtraders.Utility.NetworkChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    TextView product_name, product_category, product_discount_price, product_details_main_price, quantityNumberTextView;
    ImageView imageView, brand_image;

    RecyclerView recyclerView;
    RelatedProductAdapter relatedProductAdapter;

    FloatingActionButton add_button, minus_button;
    AppCompatButton add_to_cart, add_to_favourite;
    AppCompatImageView imageBack;

    List<AddToCartResponse.Data> data;

    ApiInterface apiInterface;

    List<ProductDetailsResponseModel> productDetails1 = new ArrayList<>();
    ProductDetailsResponseModel productDetails;

    ApiResponseModel apiResponseModel;


    int count = 1;
    int quantity;
    String product_id;

    //LatestProductModel latestProductModel = null;

    //BestSellingModel bestSellingModel = null;

    //ApiResponseModel data = null;

    String name, main_price, discount_price, thumbnail, id, slug;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initialization();
        //relateProduct();
        setListener();

        //fetchRelatedProduct();

        received_product_details();

        //setData();
        /*position = getIntent().getIntExtra("position", 0);
        System.out.println("Product Name is "+ apiResponseData.products.data.get(position).name);*/
    }

    private void initialization() {

        apiInterface = RetrofitClient.getRetrofitClient();

        product_name = findViewById(R.id.product_details_product_name);
        product_category = findViewById(R.id.product_details_product_category);
        product_details_main_price = findViewById(R.id.product_details_product_main_price);
        product_discount_price = findViewById(R.id.product_details_product_dicount_price);
        imageView = findViewById(R.id.imageView);
        brand_image = findViewById(R.id.product_details_product_brand_image);

        recyclerView = findViewById(R.id.product_details_related_product_recyclerView);

        quantityNumberTextView = findViewById(R.id.quantityNumberTextView);

        imageBack = findViewById(R.id.imageBackId);
        add_button = findViewById(R.id.add_button);
        minus_button = findViewById(R.id.minus_button);
        add_to_cart = findViewById(R.id.add_to_cart_button);
        add_to_favourite = findViewById(R.id.add_to_favourite_button);



    }


    /*private void fetchRelatedProduct() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        apiInterface.getRelatedProduct(apiResponseData.products.data.get(position).slug).enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                if (response.body() != null){
                    apiResponseData = response.body();
                    relatedProductAdapter = new RelatedProductAdapter(ProductDetailsActivity.this, apiResponseData);
                    recyclerView.setAdapter(relatedProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {

            }
        });
    }*/


    private void relateProduct() {

       /* List<RelatedProductModel> relatedProductModelList = new ArrayList<>();

        relatedProductModelList.add(new RelatedProductModel(R.drawable.img,"Capilano Manuka Active Honey 340g","1057.3৳"));
        relatedProductModelList.add(new RelatedProductModel(R.drawable.sell,"Capilano Manuka Active Honey 340g","1057.3৳"));
        relatedProductModelList.add(new RelatedProductModel(R.drawable.feature,"Capilano Manuka Active Honey 340g","1057.3৳"));

        RelatedProductAdapter relatedProductAdapter = new RelatedProductAdapter(relatedProductModelList, this);

        recyclerView.setAdapter(relatedProductAdapter);*/

        /*RetrofitClient.getRetrofitClient().getCategoryWiseProduct("milk").enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    //productDetails1.addAll(response.body());
                    relatedProductAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {

            }
        });*/
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
        slug = getIntent().getStringExtra("slug");

        //System.out.println("ID is ============ >" + apiResponseModel.products.data.get(position).id.toString());


        RetrofitClient.getRetrofitClient().getProductDetails(slug).enqueue(new Callback<ProductDetailsResponseModel>() {
            @Override
            public void onResponse(Call<ProductDetailsResponseModel> call, Response<ProductDetailsResponseModel> response) {
                if (response.body() != null){

                    productDetails = response.body();

                    System.out.println("Name is ====== >" +productDetails.getName());
                    product_name.setText(productDetails.getName());
                    product_category.setText(productDetails.category.name);
                    Glide.with(getApplicationContext()).load(productDetails.brand.image).into(brand_image);
                    Glide.with(getApplicationContext()).load(productDetails.getThumbnail()).into(imageView);
                    System.out.println("Category is ==== > " + productDetails.category.name);
                    System.out.println("Id is ==== > " + productDetails.id);
                    product_details_main_price.setText(productDetails.getPrice()+" ৳");
                    product_discount_price.setText(productDetails.getFinal_price() + " ৳");
                    product_details_main_price.setPaintFlags(product_details_main_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    quantityNumberTextView.setText(String.valueOf(count));

                }
                else {
                    System.out.println("Error=========>" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailsResponseModel> call, Throwable t) {
                System.out.println("Failure========>" + t.getMessage());
            }
        });

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

        String name = productDetails.name.trim();
        String category = productDetails.category.name.trim();
        String main_price = productDetails.price.trim();

        product_id = productDetails.id;
        double price = productDetails.final_price;
        quantity = count;

        System.out.println("Inside Cart quantity ================= > " + count);
        System.out.println("Inside Cart Product ID ================= > " + product_id);

        RetrofitClient.getRetrofitClient().addCart(quantity, product_id).enqueue(new Callback<AddCartResponse>() {
            @Override
            public void onResponse(Call<AddCartResponse> call, Response<AddCartResponse> response) {
                if (response.body() != null){
                    showToast("Success");
                }

                else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<AddCartResponse> call, Throwable t) {
                showToast(t.getMessage());
            }
        });

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

    @Override
    protected void onStart() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

}