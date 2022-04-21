package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faruqtraders.Adapter.CartAdapter;
import com.example.faruqtraders.Adapter.TopInCategoriesAdapter;
import com.example.faruqtraders.Model.CartModel;
import com.example.faruqtraders.Model.TopInCategoriesModel;
import com.example.faruqtraders.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imageView;

    RecyclerView cartRecyclerView;
    TextView totalPriceOfCart, sub_total, others, grand_total;

    AppCompatButton checkoutButton;

    int grand_total_amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initialization();
        setListener();
        fetchCartProduct();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiver, new IntentFilter("MyTotalAmount"));


        System.out.println("Grand Total is  " + String.valueOf(grand_total_amount));
        grand_total.setText(String.valueOf(grand_total_amount));

    }

    private void initialization() {

        imageView = findViewById(R.id.imageBack);
        grand_total = findViewById(R.id.grand_total);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        checkoutButton = findViewById(R.id.checkoutButton);

    }

    private void setListener(){
        imageView.setOnClickListener(this);
        checkoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                break;

            case R.id.checkoutButton:
                showToast("Checkout");
                break;
            default:
                return;
        }
    }

    private void fetchCartProduct() {
        // Top in Categories RecyclerView

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<CartModel> cartModelList = new ArrayList<>();

        cartModelList.add(new CartModel("","Capilano Manuka Active Honey 340g",500,2,0));
        cartModelList.add(new CartModel("","Capilano Manuka Active Honey 340g",1000,5, 0 ));
        cartModelList.add(new CartModel("","Capilano Manuka Active Honey 340g",200,6, 0 ));
        cartModelList.add(new CartModel("","Capilano Manuka Active Honey 340g",300,6, 0 ));
        cartModelList.add(new CartModel("","Capilano Manuka Active Honey 340g",100,5, 0 ));

        CartAdapter cartAdapter = new CartAdapter(this, cartModelList);

        cartRecyclerView.setAdapter(cartAdapter);

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
}