package com.example.faruqtraders.Activities;

import androidx.annotation.Nullable;
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
import com.example.faruqtraders.MainActivity;
import com.example.faruqtraders.R;
import com.example.faruqtraders.Request.LoginRequest;
import com.example.faruqtraders.Response.LoginResponse;
import com.example.faruqtraders.Response.UserRegisterResponse;
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

    TextView textCreateNewAccount, lostYourPasswordTextView;
    AppCompatButton signInButton;
    EditText loginEmailEditText, loginPasswordEditText;
    CircleImageView facebook, google;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
        String loginEmail = loginEmailEditText.getText().toString().trim();
        String loginPassword = loginPasswordEditText.getText().toString().trim();

        if (loginEmail.isEmpty()) {
            showToast("Enter Email");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()) {
            showToast("Enter a valid Email Address");
            return;
        } else if (loginPassword.isEmpty()) {
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


    private void Login(String email, String password, String device_name) {
        RetrofitClient.getRetrofitClient().userLogin(email, password, "").enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if (response.isSuccessful() && response.body().getToken_type().isEmpty()){

                    showToast("No token found");

                }
                else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                showToast(t.getLocalizedMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}