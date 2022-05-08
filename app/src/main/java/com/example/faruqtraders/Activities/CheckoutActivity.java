package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faruqtraders.R;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView imageView;

    String name, email;
    TextView nextTextView;

    RadioGroup radioGroup;
    RadioButton radioButton;
    int deliveryChargeInsideDhaka = 50, deliveryChargeOutsideDhaka = 90;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initialization();
        setListener();
        received_product_details();

    }

    private void initialization(){
        imageView = findViewById(R.id.imageBack);

        radioGroup = findViewById(R.id.radioGroup);
        nextTextView = findViewById(R.id.nextTextView);

    }

    private void setListener(){
        imageView.setOnClickListener(this);
        nextTextView.setOnClickListener(this);
    }

    private void received_product_details() {

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        System.out.println("Receive name is ====> + " + name + "\n Receive email is =====> " + email);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBack:
                onBackPressed();
                return;

            case R.id.nextTextView:
                sendUserDetails();
        }
    }

    private void sendUserDetails() {
        Intent intent = new Intent(getApplicationContext(), CheckoutNextActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        switch (radioId){
            case R.id.radio_one:

                showToast("Inside Dhaka deliver Charge is " + deliveryChargeInsideDhaka);
                break;

            case R.id.radio_two:
                showToast("Outside Dhaka deliver Charge is " + deliveryChargeOutsideDhaka);
                break;

            case R.id.radio_three:
                /*We can use intent here for change the activity*/
                showToast("Gulshan & Banani Free Delivery is free");
                break;
        }

        /*Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();*/
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}