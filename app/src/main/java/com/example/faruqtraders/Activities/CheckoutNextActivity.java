package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.faruqtraders.R;
import com.example.faruqtraders.Utility.NetworkChangeListener;

public class CheckoutNextActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    AppCompatImageView imageView;
    EditText nameEditText, emailEditText;

    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_next);

        initialization();
        setListener();
        received_product_details();

    }

    private void initialization(){
        imageView = findViewById(R.id.imageBack);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);

    }

    private void setListener(){
        imageView.setOnClickListener(this);

    }

    private void received_product_details(){
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        nameEditText.setText(name);
        emailEditText.setText(email);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                return;

        }
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