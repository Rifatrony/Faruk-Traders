package com.example.faruqtraders.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.faruqtraders.R;

public class DashboardActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
}