package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.faruqtraders.R;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imageView;
    TextView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        initialization();
        setListener();
    }

    private void initialization() {

        imageView = findViewById(R.id.imageBack);
        call = findViewById(R.id.phoneNumber);

    }

    private void setListener(){
        imageView.setOnClickListener(this);
        call.setOnClickListener(this);
    }

    private void makeCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+8801772333793"));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                break;

            case R.id.phoneNumber:
                makeCall();
        }
    }
}