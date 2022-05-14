package com.example.faruqtraders.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faruqtraders.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    CardView orderCard, accountsCard, wishlistCard;

    Toolbar toolbar;

    TextView userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initialization();
        setListener();


        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /*Receive user details*/
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        System.out.println("............................\n Receive Name : " + name + "\n Receive Email : "+ email + "\n Receive Phone : " + phone);

    }

    private void initialization() {
        toolbar = findViewById(R.id.toolBar);
        orderCard = findViewById(R.id.orderCard);
        accountsCard = findViewById(R.id.accountsCard);
        wishlistCard = findViewById(R.id.wishlistCard);
        userNameText = findViewById(R.id.userNameText);
    }

    private void setListener() {
        orderCard.setOnClickListener(this);
        accountsCard.setOnClickListener(this);
        wishlistCard.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dashboard_navigation_item, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_order:
                Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_account_details:
                startActivity(new Intent(getApplicationContext(), AccountDetailsActivity.class));
                Toast.makeText(this, "nav_account_details", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_change_password:
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
                Toast.makeText(this, "nav_change_password", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_wishlist:
                Toast.makeText(this, "nav_wishlist", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.orderCard:
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                break;

            case R.id.accountsCard:
                startActivity(new Intent(getApplicationContext(), AccountDetailsActivity.class));
                break;

            case R.id.wishlistCard:
                startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
                break;

        }
    }
}