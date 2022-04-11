package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

import com.example.faruqtraders.R;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initialization();
        setListener();
    }

    private void initialization() {

        imageView = findViewById(R.id.imageBack);


    }

    private void setListener(){
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
        }

    }
}