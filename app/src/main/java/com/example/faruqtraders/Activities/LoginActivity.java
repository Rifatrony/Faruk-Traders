package com.example.faruqtraders.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.LoginResponse;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textCreateNewAccount, lostYourPasswordTextView;
    AppCompatButton signInButton;
    EditText loginEmailEditText, loginPasswordEditText;
    CircleImageView facebook, google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();
        setListener();
    }

    private void initialization() {
        textCreateNewAccount = findViewById(R.id.textCreateNewAccount);
        lostYourPasswordTextView = findViewById(R.id.lostYourPassword);
        signInButton = findViewById(R.id.loginButton);

        loginEmailEditText = findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);

        facebook= findViewById(R.id.facebookLogin);
        google= findViewById(R.id.googleLogin);
    }

    private void setListener(){
        textCreateNewAccount.setOnClickListener(this);
        lostYourPasswordTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        facebook.setOnClickListener(this);
        google.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textCreateNewAccount:
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
                break;
            case R.id.lostYourPassword:
                showToast("Lost Your Password");
                break;

            case R.id.loginButton:
                userLogin();
                break;

            case R.id.facebookLogin:
                showToast("Facebook Login");
                break;

            case R.id.googleLogin:
                showToast("Google Login");
                break;
        }
    }

    private void userLogin(){
        String loginEmail = loginEmailEditText.getText().toString().trim();
        String loginPassword = loginPasswordEditText.getText().toString().trim();

        if (loginEmail.isEmpty()){
            showToast("Enter Email");
            return;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()){
            showToast("Enter a valid Email Address");
            return;
        }

        else if (loginPassword.isEmpty()){
            showToast("Enter Password");
            return;
        }

        else if (loginPassword.length() < 6){
            showToast("Minimum Password is 6");
            return;
        }

        else {
            Login(loginEmail, loginPassword, "");

            //Login();
            //Login(loginEmail, loginPassword, "");
            /*showToast("Call Api Now and work on login now");
            RetrofitClient.getRetrofitClient().userLogin(loginEmail, loginPassword,"").enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    LoginResponse obj = response.body();
                    String output = obj.getMessage();
                    Log.e("TAG", response.message());

                    *//*if user failed to log in*//*
                    if (output.equals("The given data was invalid.")){
                        showToast("Login Failed...");
                    }

                    *//*If user exist*//*
                    else if (output.equals("You are already signed in")){
                        showToast("Login successful");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });
*/
        }
    }


    private void Login( String email, String password, String device_name) {
        RetrofitClient.getRetrofitClient().userLogin(email, password, device_name).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse data = response.body();
                String output = data.getMessage();
                System.out.println("Printing the Response==================>>>" + data);

                if(response.body() != null){
                    if (output.equals("The given data was invalid.")){
                        showToast("Error");
                    }
                    else if (output.equals("You are already signed in")){
                        showToast("Success...");
                    }
                }


                /*try {
                    showToast("Successful "+response.message());
                    System.out.println("Response is =================>"+response.body().getMessage());
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Exception...... "+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    //showToast("Exception...... "+e.getLocalizedMessage());
                }*/

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showToast("Failure "+t.getLocalizedMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}