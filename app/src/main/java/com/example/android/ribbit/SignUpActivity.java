package com.example.android.ribbit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.ribbit.Constants.Constants;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUserName,mPassword,mEmail;
    protected Button mSignUpButton;
    ProgressBar prg_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mUserName = (EditText)findViewById(R.id.usernameField);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mEmail = (EditText)findViewById(R.id.emailField);
        mSignUpButton = (Button)findViewById(R.id.sign_up_button);
        prg_bar = (ProgressBar)findViewById(R.id.prg_bar);
       mSignUpButton.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
                 prg_bar.setVisibility(ProgressBar.VISIBLE);
                 if (mUserName.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty()
                         || mEmail.getText().toString().isEmpty()){
                     prg_bar.setVisibility(ProgressBar.INVISIBLE);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.sign_up_error_message);
                    builder.setTitle(R.string.sign_up_error_title);
                    builder.setPositiveButton(R.string.ok,null);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else
                {
                    newUser();
                }

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void newUser() {
        //create a new user account
        prg_bar.setVisibility(ProgressBar.VISIBLE);
        Firebase ref = new Firebase(Constants.FIREBASE_URL);
        AuthData auth = ref.getAuth();
        ref.createUser(mEmail.getText().toString(), mPassword.getText().toString()
                , new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                prg_bar.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(SignUpActivity.this, "Successfully created user account with uid: ",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                prg_bar.setVisibility(ProgressBar.INVISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setMessage(firebaseError.getMessage());
                builder.setTitle(R.string.sign_up_error_title);
                builder.setPositiveButton(R.string.ok,null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
