package com.example.android.ribbit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ribbit.Constants.Constants;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {

    protected EditText mPassword,mEmail;
    protected Button mLoginButton;
    protected TextView mSignUp;
    ProgressBar pg_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSignUp = (TextView)findViewById(R.id.signUpText);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mEmail = (EditText)findViewById(R.id.emailField);
        mLoginButton = (Button)findViewById(R.id.loginButton);
        pg_bar = (ProgressBar)findViewById(R.id.pg_bar);
        //get username and Password
       String email = mEmail.getText().toString();
       String password = mPassword.getText().toString();
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the fields are empty
                if (mEmail.getText().toString().isEmpty()|| mPassword.getText().toString().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.sign_up_error_message);
                    builder.setTitle(R.string.sign_up_error_title);
                    builder.setPositiveButton(R.string.ok,null);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                //validate the user
                else
                valiDateUser();
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
            }
        });

    }

    private void valiDateUser() {

        Firebase ref = new Firebase(Constants.FIREBASE_URL);
        setProgressBarIndeterminateVisibility(true);
        pg_bar.setVisibility(ProgressBar.VISIBLE);
        ref.authWithPassword(mEmail.getText().toString(), mPassword.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                setProgressBarIndeterminateVisibility(false);
                pg_bar.setVisibility(ProgressBar.INVISIBLE);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(firebaseError.getMessage());
                builder.setTitle(R.string.sign_up_error_title);
                builder.setPositiveButton(R.string.ok,null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                pg_bar.setVisibility(ProgressBar.INVISIBLE);
            }
        });

    }

}
