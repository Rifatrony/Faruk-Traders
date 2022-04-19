package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.faruqtraders.API.ApiInterface;
import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.Adapter.FeatureAdapter;
import com.example.faruqtraders.Adapter.LatestProductAdapter;
import com.example.faruqtraders.Adapter.TopCategoriesMoreProductAdapter;
import com.example.faruqtraders.Adapter.TopInCategoriesAdapter;
import com.example.faruqtraders.MainActivity;
import com.example.faruqtraders.Model.FeatureModel;
import com.example.faruqtraders.Model.LatestProductModel;
import com.example.faruqtraders.Model.TopCategoriesMoreProductModel;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.ApiResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imageView;
    RecyclerView top_in_category_recycler_view;
    TopCategoriesMoreProductAdapter adapter;

    ApiInterface apiInterface;
    ApiResponseModel data;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_category);

        initialization();
        setListener();

        fetchTopCategoryMoreProduceProduct();

    }

    private void initialization(){

        apiInterface = RetrofitClient.getRetrofitClient();

        progressDialog = new ProgressDialog(this);

        imageView = findViewById(R.id.imageBack);
        top_in_category_recycler_view = findViewById(R.id.top_in_categories_more_product_recyclerView);

    }

    private void setListener(){
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                break;

            default:
                return;
        }
    }


    private void fetchTopCategoryMoreProduceProduct() {
        top_in_category_recycler_view.setLayoutManager(new GridLayoutManager(this, 2));

        progressDialog.show();
        progressDialog.setMessage("Loading Please Wait");
        progressDialog.setCancelable(false);

        apiInterface.getTopInCategories().enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                progressDialog.dismiss();

                if (response.body() != null){
                    data = response.body();
                    adapter = new TopCategoriesMoreProductAdapter(TopCategoryActivity.this, data);
                    top_in_category_recycler_view.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {

               progressDialog.dismiss();

            }
        });
    }


}