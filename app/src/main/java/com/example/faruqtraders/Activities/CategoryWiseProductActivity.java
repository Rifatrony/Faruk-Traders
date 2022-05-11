package com.example.faruqtraders.Activities;

import static com.example.faruqtraders.MainActivity.apiResponseData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faruqtraders.API.ApiInterface;
import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.Adapter.CategoryDetailsAdapter;
import com.example.faruqtraders.Adapter.PeopleAreAlsoLookingForAdapter;
import com.example.faruqtraders.Adapter.TopCategoriesMoreProductAdapter;
import com.example.faruqtraders.MainActivity;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.ApiResponseModel;
import com.example.faruqtraders.Response.CategoryResponseModel;
import com.example.faruqtraders.Response.FilterResponseModel;
import com.example.faruqtraders.Response.VisitedProductResponse;
import com.example.faruqtraders.Utility.NetworkChangeListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryWiseProductActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    CategoryResponseModel categoryModel;
    FilterResponseModel filterResponseModel;
    String slug, name, icon;
    int position;

    RecyclerView recyclerView;
    TextView titleTextView;
    AppCompatImageView imageBack;

    CategoryDetailsAdapter detailsAdapter;
    ApiInterface apiInterface;
    VisitedProductResponse visitedProductResponse;
    ApiResponseModel apiResponseModel;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wise_product_acivity);

        initialization();

        setListener();
        fetchCategories();

        //position = getIntent().getIntExtra("position", 0);

        slug = getIntent().getStringExtra("slug");
        name = getIntent().getStringExtra("name");
        icon = getIntent().getStringExtra("icon");

        System.out.println("Slug is =======>" + slug);
        System.out.println("Name is =======>" + name);
        System.out.println("Icon is =======>" + icon);

        titleTextView.setText(name);
    }

    private void initialization(){

        apiInterface = RetrofitClient.getRetrofitClient();

        progressDialog = new ProgressDialog(this);

        imageBack = findViewById(R.id.imageBackId);

        titleTextView = findViewById(R.id.title);
        recyclerView = findViewById(R.id.eachCategoryRecyclerView);

    }

    private void setListener(){
        imageBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBackId:
                onBackPressed();
                break;
            default:
                return;
        }
    }

    private void fetchCategories() {

        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Please Wait...");
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        apiInterface.getCategoryWiseProduct("biscuitschips", 2).enqueue(new Callback<ApiResponseModel>() {

            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                if (response.body() != null){

                    progressDialog.dismiss();

                    apiResponseModel = response.body();
                    detailsAdapter = new CategoryDetailsAdapter(CategoryWiseProductActivity.this, apiResponseModel);
                    recyclerView.setAdapter(detailsAdapter);
                    Toast.makeText(getApplicationContext(),slug, Toast.LENGTH_SHORT).show();

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(CategoryWiseProductActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CategoryWiseProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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