package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faruqtraders.R;

public class AccountDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText updateNameEditText, updateEmailEditText, updatePhoneEditText, updateAddressEditText;

    AppCompatButton saveChangesButton;
    AppCompatImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);


        initialization();
        setListener();

    }

    private void initialization() {
        updateNameEditText = findViewById(R.id.updateNameEditText);
        updateEmailEditText = findViewById(R.id.updateEmailEditText);
        updatePhoneEditText = findViewById(R.id.updatePhoneEditText);
        updateAddressEditText = findViewById(R.id.updateAddressEditText);

        saveChangesButton = findViewById(R.id.saveChangesButton);
        imageBack = findViewById(R.id.imageBack);

    }

    private void setListener(){
        saveChangesButton.setOnClickListener(this);
        imageBack.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveChangesButton:
                fetchUserDetails();
                break;

            case R.id.imageBack:
                onBackPressed();
                break;
        }
    }

    private void fetchUserDetails(){
        Toast.makeText(this, "Fetch Data", Toast.LENGTH_SHORT).show();
    }
}