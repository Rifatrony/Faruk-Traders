package com.example.faruqtraders.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faruqtraders.API.ApiInterface;
import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.Adapter.CartAdapter;
import com.example.faruqtraders.Adapter.CartDetailsAdapter;
import com.example.faruqtraders.Adapter.TopInCategoriesAdapter;
import com.example.faruqtraders.MainActivity;
import com.example.faruqtraders.Model.CartModel;
import com.example.faruqtraders.Model.TopInCategoriesModel;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.ApiResponseModel;
import com.example.faruqtraders.Response.CartResponseModel;
import com.example.faruqtraders.Utility.NetworkChangeListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    AppCompatImageView imageView;

    RecyclerView cartRecyclerView;
    TextView grand_total;

    AppCompatButton checkoutButton;

    int grand_total_amount = 0;
    CartResponseModel cartResponseModelList;
    ApiInterface apiInterface;
    CartDetailsAdapter adapter;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initialization();
        setListener();
        fetchCartProduct();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiver, new IntentFilter("MyTotalAmount"));


        System.out.println("Grand Total is  " + grand_total_amount);
        grand_total.setText(String.valueOf(grand_total_amount));

    }

    private void initialization() {

        apiInterface = RetrofitClient.getRetrofitClient();

        cartRecyclerView = findViewById(R.id.cartRecyclerView);

        imageView = findViewById(R.id.imageBack);
        grand_total = findViewById(R.id.grand_total);

        checkoutButton = findViewById(R.id.checkoutButton);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        acct = GoogleSignIn.getLastSignedInAccount(this);

    }

    private void setListener(){
        imageView.setOnClickListener(this);
        checkoutButton.setOnClickListener(this);
    }

    private void checkLogin(){
        if (acct != null){
            /*showToast(acct.getDisplayName());
            showToast(acct.getId());*/
            showToast("Go for Checkout");
            Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
            intent.putExtra("name", acct.getDisplayName());
            intent.putExtra("email", acct.getEmail());
            startActivity(intent);
            //startActivity(new Intent(getApplicationContext(), CheckoutActivity.class));

        }

        else {
            showToast("Need to Login");
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                break;

            case R.id.checkoutButton:
                checkLogin();
                break;
            default:
        }
    }

    private void fetchCartProduct() {

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitClient.getRetrofitClient().getCartDetails().enqueue(new Callback<CartResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<CartResponseModel> call, Response<CartResponseModel> response) {
                if (response.body() != null){
                    cartResponseModelList = response.body();
                    adapter = new CartDetailsAdapter(CartActivity.this,cartResponseModelList);
                    cartRecyclerView.setAdapter(adapter);

                    Toast.makeText(CartActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(CartActivity.this, "Error Body "+response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponseModel> call, Throwable t) {

                System.out.println("Failure "+ t.getMessage());
                Toast.makeText(CartActivity.this,"Failure"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            grand_total_amount = intent.getIntExtra("totalAmount", 0);
            grand_total.setText(String.valueOf(grand_total_amount) +" Tk.");
            System.out.println("Sub Total Amount is ======> " + grand_total_amount);
        }
    };

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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