package com.example.android.ribbit;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by surya on 02-06-2016.
 */
public class RibbitApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
