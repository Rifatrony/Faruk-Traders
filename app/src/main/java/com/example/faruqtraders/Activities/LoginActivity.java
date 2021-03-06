package com.example.faruqtraders.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faruqtraders.API.RetrofitClient;
import com.example.faruqtraders.MainActivity;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Response.LoginResponse;
import com.example.faruqtraders.Response.UserRegisterResponse;
import com.example.faruqtraders.Utility.NetworkChangeListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    TextView textCreateNewAccount, lostYourPasswordTextView;
    AppCompatButton signInButton;
    EditText loginNumberEditText, loginPasswordEditText;
    CircleImageView facebook, google;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    LoginResponse loginResponse;

    ProgressBar progressBar;


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

        loginNumberEditText = findViewById(R.id.loginNumberEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);

        progressBar = findViewById(R.id.progressBar);

        facebook = findViewById(R.id.facebookLogin);
        google = findViewById(R.id.googleLogin);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

    }

    private void setListener() {
        textCreateNewAccount.setOnClickListener(this);
        lostYourPasswordTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        facebook.setOnClickListener(this);
        google.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textCreateNewAccount:
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
                break;
            case R.id.lostYourPassword:
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
                break;

            case R.id.loginButton:
                userLogin();
                break;

            case R.id.facebookLogin:
                showToast("Facebook Login");
                break;

            case R.id.googleLogin:
                googleLogin();
                break;
        }
    }

    private void googleLogin(){
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                sendToSecondActivity();
                showToast("Login Successful");

            }catch (ApiException e){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendToSecondActivity(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void userLogin() {
        String loginEmail = loginNumberEditText.getText().toString().trim();
        String loginPassword = loginPasswordEditText.getText().toString().trim();

        if (loginEmail.isEmpty()) {
            showToast("Enter Number");
            return;
        }
        else if (loginPassword.isEmpty()) {
            showToast("Enter Password");
            return;

        } else if (loginPassword.length() < 6) {
            showToast("Minimum Password is 6");
            return;
        } else {
            //login();
            Login(loginEmail, loginPassword, "");

        }
    }

    /*Phone Number Login*/

    private void Login(String phone, String password, String device_name) {

        signInButton.setVisibility(View.INVISIBLE);

        progressBar.setVisibility(View.VISIBLE);


        RetrofitClient.getRetrofitClient().userLogin(phone, password, "redmi").enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if (response.body() != null){
                    signInButton.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    showToast("Login Success");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                showToast(t.getLocalizedMessage());

                signInButton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showToast(String message) {
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